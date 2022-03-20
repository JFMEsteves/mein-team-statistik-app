package de.fhswf.statistics;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.fhswf.statistics.api.OnFailureListener;
import de.fhswf.statistics.api.OnSuccessListener;
import de.fhswf.statistics.api.parser.ParsingException;
import de.fhswf.statistics.api.service.SpielerService;
import de.fhswf.statistics.model.SpielSpieler;
import de.fhswf.statistics.model.Spieler;

public class MockService  implements SpielerService {
    public static final int DELAY = 2000;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final ArrayList<Spieler> spielerList;
    private boolean generateNegativeResponses;
    public MockService() {
        this.spielerList = new ArrayList<>();
        fillExampleSpieler();

    }
    public MockService(boolean generateNegativeResponses) {
        this();

        this.generateNegativeResponses = generateNegativeResponses;
    }



    private boolean isGenerateNegativeResponses() {return generateNegativeResponses;
    }


    private void fillExampleSpieler() {
        spielerList.add(new Spieler(1,"Marcel").addstats(new SpielSpieler(1,1,13,5,2,6,1)
                    ).addstats(new SpielSpieler(1,2,9,3,3,1,4)
                    ).addstats(new SpielSpieler(1,3,45,12,8,4,2)
                    ).addstats(new SpielSpieler(1,4,30,20,20,1,0)));
        spielerList.add(new Spieler(2,"Joey").addstats(new SpielSpieler(2,1,5,6,5,0,0)));
        spielerList.add(new Spieler(3,"Tahiry").addstats(new SpielSpieler(3,1,14,2,2,3,1)));
        spielerList.add(new Spieler(4,"Leon").addstats(new SpielSpieler(4,1,14,2,2,3,1)));
        spielerList.add(new Spieler(5,"Paul").addstats(new SpielSpieler(5,1,100,50,40,30,1)));
        spielerList.add(new Spieler(6,"Lennard").addstats(new SpielSpieler(6,2,9,0,0,3,0)));
        spielerList.add(new Spieler(7,"Dimi").addstats(new SpielSpieler(7,4,1400,10,20,0,1)));
        spielerList.add(new Spieler(8,"Björn").addstats(new SpielSpieler(8,1,14,2,2,3,1)));
        spielerList.add(new Spieler(9,"Sven").addstats(new SpielSpieler(9,1,14,2,2,3,1)));
        spielerList.add(new Spieler(10,"Tolga").addstats(new SpielSpieler(10,1,14,2,2,3,1)));
        spielerList.add(new Spieler(11,"Mike").addstats(new SpielSpieler(11,1,14,2,2,3,1)));
        spielerList.add(new Spieler(12,"Jonas").addstats(new SpielSpieler(12,1,14,2,2,3,1)));
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
    public void fetchSpielerDetails(int id,
            @Nullable OnSuccessListener<Spieler> onSuccessListener, @Nullable OnFailureListener onFailureListener) {

    }
    private void errorCall(String source, @Nullable OnFailureListener onFailureListener) {
        if (onFailureListener != null) {
            handler.postDelayed(() -> onFailureListener.onFailure(
                    new ParsingException("Mock-Error for %s!", source)), DELAY);
        }
    }
}
