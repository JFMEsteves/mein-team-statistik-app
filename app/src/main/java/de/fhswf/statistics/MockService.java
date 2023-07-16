package de.fhswf.statistics;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.fhswf.statistics.api.OnFailureListener;
import de.fhswf.statistics.api.OnSuccessListener;
import de.fhswf.statistics.api.parser.ParsingException;
import de.fhswf.statistics.api.service.SpielerService;
import de.fhswf.statistics.model.Spiel;
import de.fhswf.statistics.model.SpielSpieler;
import de.fhswf.statistics.model.Spieldetails;
import de.fhswf.statistics.model.Spieler;

/**
 * Test-Service um das Frontend getrennt vom Backend entwickeln zu können. Implementiert Mock Klassen & Daten.
 *
 * @see de.fhswf.statistics.api.service.SpielerService
 */
public class MockService implements SpielerService {
    public static final int DELAY = 2000;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final ArrayList<Spieler> spielerList;
    private ArrayList<Spiel> spielelist;
    private boolean generateNegativeResponses;

    public MockService() {
        this.spielerList = new ArrayList<>();
        this.spielelist = new ArrayList<>();
        fillExampleSpieler();
        fillExampleSpiele();

    }

    public MockService(boolean generateNegativeResponses) {
        this();

        this.generateNegativeResponses = generateNegativeResponses;
    }


    private boolean isGenerateNegativeResponses() {
        return generateNegativeResponses;
    }

    /**
     * Testdaten die eine Antwort List<Spieler> vom call an das Backend simulieren
     */
    private void fillExampleSpieler() {
        Spieler spieler1 = new Spieler(1, "Marcel");
        Spieler spieler2 = new Spieler(2, "Joey");
        Spieler spieler3 = new Spieler(3, "Tahiry");
        Spieler spieler4 = new Spieler(4, "Leon");
        Spieler spieler5 = new Spieler(5, "Paul");
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Spiel spiel1 = new Spiel(1, df.parse("24.01.2020"), 10, 20, "Holzkeks");
            Spiel spiel2 = new Spiel(2, df.parse("20.02.2019"), 20, 30, "Steckenpferd");
            Spiel spiel3 = new Spiel(3, df.parse("16.03.2018"), 30, 40, "Hagen");
            Spiel spiel4 = new Spiel(4, df.parse("12.04.2017"), 40, 50, "Bierbären");
            Spiel spiel5 = new Spiel(5, df.parse("08.05.2016"), 50, 60, "Fernsehköche");


            SpielSpieler stats1 = new SpielSpieler(1, 1, 1, 1, 1, 1, 1, spiel1, spieler1);
            SpielSpieler stats2 = new SpielSpieler(2, 2, 2, 2, 2, 2, 2, spiel2, spieler2);
            SpielSpieler stats3 = new SpielSpieler(3, 3, 3, 3, 3, 3, 3, spiel3, spieler3);
            SpielSpieler stats4 = new SpielSpieler(4, 4, 4, 4, 4, 4, 4, spiel4, spieler4);
            SpielSpieler stats5 = new SpielSpieler(5, 5, 5, 5, 5, 5, 5, spiel5, spieler5);

            SpielSpieler stats1zwei = new SpielSpieler(2, 1, 1, 1, 1, 1, 1, spiel1, spieler2);
            SpielSpieler stats1drei = new SpielSpieler(3, 1, 1, 1, 1, 1, 1, spiel1, spieler3);
            SpielSpieler stats1vier = new SpielSpieler(4, 1, 1, 1, 1, 1, 1, spiel1, spieler4);
            SpielSpieler stats1fuenf = new SpielSpieler(5, 1, 1, 1, 1, 1, 1, spiel1, spieler5);
            SpielSpieler stats2zwei = new SpielSpieler(3, 2, 2, 2, 2, 2, 2, spiel2, spieler3);
            SpielSpieler stats2drei = new SpielSpieler(4, 2, 2, 2, 2, 2, 2, spiel2, spieler4);
            SpielSpieler stats2vier = new SpielSpieler(5, 2, 2, 2, 2, 2, 2, spiel2, spieler5);
            SpielSpieler stats3zwei = new SpielSpieler(2, 3, 3, 3, 3, 3, 3, spiel3, spieler2);
            SpielSpieler stats3drei = new SpielSpieler(4, 3, 3, 3, 3, 3, 3, spiel3, spieler4);
            SpielSpieler stats3vier = new SpielSpieler(5, 3, 3, 3, 3, 3, 3, spiel3, spieler5);

            Spiel spielmarcel = new Spiel();
            spielmarcel.setId(1);
            spielmarcel.setTeamname("Hagen");
            Spieler spielermarcel = new Spieler();
            spielermarcel.setId(1);
            spielermarcel.setName("Marcel");
            spielerList.add(spieler1.addstats(stats1));
            spielerList.add(spieler2.addstats(stats2).addstats(stats3zwei));
            spielerList.add(spieler3.addstats(stats3).addstats(stats1drei).addstats(stats2zwei));
            spielerList.add(spieler4.addstats(stats4).addstats(stats1vier).addstats(stats2drei).addstats(stats3drei));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void fillExampleSpiele() {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Spieler spieler1 = new Spieler(1, "Marcel");
            Spieler spieler2 = new Spieler(2, "Joey");
            Spieler spieler3 = new Spieler(3, "Tahiry");
            Spieler spieler4 = new Spieler(4, "Leon");
            Spieler spieler5 = new Spieler(5, "Paul");
            Spiel spiel1 = new Spiel(1, df.parse("24.01.2020"), 10, 20, "Holzkeks");
            Spiel spiel2 = new Spiel(2, df.parse("20.02.2019"), 20, 30, "Steckenpferd");
            Spiel spiel3 = new Spiel(3, df.parse("16.03.2018"), 30, 40, "Hagen");
            Spiel spiel4 = new Spiel(4, df.parse("12.04.2017"), 40, 50, "Bierbären");
            Spiel spiel5 = new Spiel(5, df.parse("08.05.2016"), 50, 60, "Fernsehköche");


            SpielSpieler stats1 = new SpielSpieler(1, 1, 1, 1, 1, 1, 1, spiel1, spieler1);
            SpielSpieler stats2 = new SpielSpieler(2, 2, 2, 2, 2, 2, 2, spiel2, spieler2);
            SpielSpieler stats3 = new SpielSpieler(3, 3, 3, 3, 3, 3, 3, spiel3, spieler3);
            SpielSpieler stats4 = new SpielSpieler(4, 4, 4, 4, 4, 4, 4, spiel4, spieler4);
            SpielSpieler stats5 = new SpielSpieler(5, 5, 5, 5, 5, 5, 5, spiel5, spieler5);

            SpielSpieler stats1zwei = new SpielSpieler(2, 1, 1, 1, 1, 1, 1, spiel1, spieler2);
            SpielSpieler stats1drei = new SpielSpieler(3, 1, 1, 1, 1, 1, 1, spiel1, spieler3);
            SpielSpieler stats1vier = new SpielSpieler(4, 1, 1, 1, 1, 1, 1, spiel1, spieler4);
            SpielSpieler stats1fuenf = new SpielSpieler(5, 1, 1, 1, 1, 1, 1, spiel1, spieler5);
            SpielSpieler stats2zwei = new SpielSpieler(3, 2, 2, 2, 2, 2, 2, spiel2, spieler3);
            SpielSpieler stats2drei = new SpielSpieler(4, 2, 2, 2, 2, 2, 2, spiel2, spieler4);
            SpielSpieler stats2vier = new SpielSpieler(5, 2, 2, 2, 2, 2, 2, spiel2, spieler5);
            SpielSpieler stats3zwei = new SpielSpieler(2, 3, 3, 3, 3, 3, 3, spiel3, spieler2);
            SpielSpieler stats3drei = new SpielSpieler(4, 3, 3, 3, 3, 3, 3, spiel3, spieler4);
            SpielSpieler stats3vier = new SpielSpieler(5, 3, 3, 3, 3, 3, 3, spiel3, spieler5);


            spielelist.add(spiel1.addstats(stats1)
                    .addstats(stats1zwei)
                    .addstats(stats1drei)
                    .addstats(stats1vier)
                    .addstats(stats1fuenf)
                    .adddetails(new Spieldetails(1, 0, 1, 2, 3, 4, spiel1))
                    .adddetails(new Spieldetails(1, 1, 1, 2, 3, 4, spiel1)));
            spielelist.add(spiel2.addstats(stats2)
                    .addstats(stats2zwei)
                    .addstats(stats2drei)
                    .addstats(stats2vier)
                    .adddetails(new Spieldetails(2, 0, 1, 2, 3, 4, spiel2))
                    .adddetails(new Spieldetails(2, 1, 1, 2, 3, 4, spiel2)));
            spielelist.add(spiel3.addstats(stats3)
                    .addstats(stats3zwei)
                    .addstats(stats3drei)
                    .addstats(stats3vier)
                    .adddetails(new Spieldetails(3, 0, 1, 2, 3, 4, spiel3))
                    .adddetails(new Spieldetails(3, 1, 1, 2, 3, 4, spiel3)));
            spielelist.add(spiel4.addstats(stats4)
                    .adddetails(new Spieldetails(4, 0, 1, 2, 3, 4, spiel4))
                    .adddetails(new Spieldetails(4, 1, 1, 2, 3, 4, spiel4)));
            spielelist.add(spiel5.addstats(stats5)
                    .adddetails(new Spieldetails(5, 0, 1, 2, 3, 4, spiel5))
                    .adddetails(new Spieldetails(5, 1, 1, 2, 3, 4, spiel5)));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fetchSpielerList(@Nullable OnSuccessListener<List<Spieler>> onSuccessListener, @Nullable OnFailureListener onFailureListener) {
        if (isGenerateNegativeResponses()) {
            errorCall("FetchSpielerList", onFailureListener);
        } else if (onSuccessListener != null) {
            handler.postDelayed(() -> onSuccessListener.onSuccess(
                    Collections.unmodifiableList(spielerList)), DELAY);
        }
    }

    @Override
    public void fetchSpielList(@Nullable OnSuccessListener<List<Spiel>> onSuccessListener, @Nullable OnFailureListener onFailureListener) {
        if (isGenerateNegativeResponses()) {
            errorCall("FetchSpielList", onFailureListener);
        } else if (onSuccessListener != null) {
            handler.postDelayed(() -> onSuccessListener.onSuccess(Collections.unmodifiableList(spielelist)), DELAY);
        }
    }

    @Override
    public void submitSpiel(@NonNull JSONObject content, @Nullable OnSuccessListener<Void> onSuccessListener, @Nullable OnFailureListener onFailureListener) {
        if (isGenerateNegativeResponses()) {
            errorCall("SubmitSpiel", onFailureListener);
        } else if (onSuccessListener != null) {
            handler.postDelayed(() -> onSuccessListener.onSuccess(null), DELAY);
        }
    }

    @Override
    public void fetchSpielerDetails(int id,
                                    @Nullable OnSuccessListener<Spieler> onSuccessListener, @Nullable OnFailureListener onFailureListener) {
        if (isGenerateNegativeResponses()) {
            errorCall("FetchSpielerDetails", onFailureListener);
        } else if (onSuccessListener != null) {

            handler.postDelayed(() -> {
                for (Spieler c : spielerList) {
                    if (c.getId() == id) {
                        onSuccessListener.onSuccess(c);
                        return;
                    }
                }

                if (onFailureListener != null)
                    onFailureListener.onFailure(new RuntimeException("Spieler not found! (mock)"));
            }, DELAY);
        }
    }

    @Override
    public void fetchSpielDetails(int id,
                                  @Nullable OnSuccessListener<Spiel> onSuccessListener, @Nullable OnFailureListener onFailureListener) {
        if (isGenerateNegativeResponses()) {
            errorCall("FetchSpielerDetails", onFailureListener);
        } else if (onSuccessListener != null) {

            handler.postDelayed(() -> {
                for (Spiel c : spielelist) {
                    if (c.getId() == id) {
                        onSuccessListener.onSuccess(c);
                        return;
                    }
                }

                if (onFailureListener != null)
                    onFailureListener.onFailure(new RuntimeException("Spieler not found! (mock)"));
            }, DELAY);
        }
    }

    private void errorCall(String source, @Nullable OnFailureListener onFailureListener) {
        if (onFailureListener != null) {
            handler.postDelayed(() -> onFailureListener.onFailure(
                    new ParsingException("Mock-Error for %s!", source)), DELAY);
        }
    }
}
