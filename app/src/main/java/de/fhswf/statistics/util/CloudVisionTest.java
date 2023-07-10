package de.fhswf.statistics.util;

import android.graphics.Bitmap;
import android.util.Base64;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.ImageAnnotatorSettings;
import com.google.protobuf.ByteString;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.grpc.StatusRuntimeException;


public class CloudVisionTest {
    public String jsonContent = "Hier presönlichen String einbinden";
    private StringBuilder result;

    public CloudVisionTest() {

    }


    public String extractTextFromImage(Bitmap bitmap) {
        try {
            // Lade die Anmeldeinformationen aus der JSON-Datei
            GoogleCredentials credentials = GoogleCredentials.fromStream(new ByteArrayInputStream(jsonContent.getBytes()))
                    .createScoped(Collections.singletonList("https://www.googleapis.com/auth/cloud-platform"));

            if (bitmap == null) {
                System.err.println("Bitmap is null");
            }
            List<AnnotateImageRequest> requests = new ArrayList<>();

            // Add the image
            Image base64EncodedImage;
            // Convert the bitmap to a JPEG
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
            byte[] imageBytes = byteArrayOutputStream.toByteArray();

            // Base64 encode the JPEG
            String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            // Erstelle ein ByteString base64 kodiert
            ByteString byteString = ByteString.copyFrom(Base64.decode(encodedImage, Base64.DEFAULT));
            // Erstelle ein Image-Objekt base64 kodiert
            base64EncodedImage = Image.newBuilder()
                    .setContent(byteString)
                    .build();


            // Erstelle ein Feature-Objekt
            //DOCUMENT_TEXT_DETECTION für die Texterkennung von viel Text innerhalb eines Bildes
            Feature feat = Feature.newBuilder().setType(Feature.Type.DOCUMENT_TEXT_DETECTION).build();

            AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
                    .addFeatures(feat)
                    .setImage(base64EncodedImage).build();


            requests.add(request);


            // Erstelle den Vision-Client mit den Anmeldeinformationen
            ImageAnnotatorSettings settings = ImageAnnotatorSettings.newBuilder().setCredentialsProvider(FixedCredentialsProvider.create(credentials)).build();
            try (ImageAnnotatorClient visionClient = ImageAnnotatorClient.create(settings)) {

                BatchAnnotateImagesResponse response = visionClient.batchAnnotateImages(requests);
                List<AnnotateImageResponse> responses = response.getResponsesList();

                result = new StringBuilder();
                for (AnnotateImageResponse res : responses) {
                    if (res.hasError()) {
                        System.err.println("Error: " + res.getError().getMessage());
                    } else {
                        for (EntityAnnotation annotation : res.getTextAnnotationsList()) {
                            result.append(annotation.getDescription()).append("\n");
                            //Log.d("looking into answer ", "detectTextFromImage: " + result);
                            System.out.format("Text: %s%n", annotation.getDescription());
                            System.out.format("Position : %s%n", annotation.getBoundingPoly());


                        }
                    }
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (StatusRuntimeException e) {
            System.err.println("An error occurred while calling the Cloud Vision API: " + e.getMessage());
            e.printStackTrace();
        }

        return result.toString();
    }
}