package blog.cosmos.home.sangeetai.activity;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import  blog.cosmos.home.sangeetai.AppController;
import blog.cosmos.home.sangeetai.MainActivity;
import blog.cosmos.home.sangeetai.R;
import blog.cosmos.home.sangeetai.constants.MoodConstants;
import com.example.jean.jcplayer.model.JcAudio;
import com.example.jean.jcplayer.view.JcPlayerView;

import java.util.ArrayList;
import java.util.Random;

public class ExpressionDisplayActivity extends AppCompatActivity {

    ConstraintLayout expressionBackgroundRL;
    ImageView emojiPlaceholderIV;
    Handler handler;
    Runnable runnable;
    MediaPlayer mediaPlayer;
    String musicUrl = "";




    ListView lv;
    String mood;
    String songNameval, songUrlval;
    JcPlayerView jcplayer;


    boolean isSad = false;

    ArrayList<String> arrSadSongName = new ArrayList<>();
    ArrayList<String> arrHappySongName = new ArrayList<>();

    ArrayList<String> arrsongUrl = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    ArrayList<JcAudio> jcAudios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        ExpressionDisplayActivity.this.getWindow().setStatusBarColor(getResources().getColor(R.color.customStatusbarColor));
        View decorView = ExpressionDisplayActivity.this.getWindow().getDecorView();
        decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_expression_display);
        init();
        setMood();
        startTheMusic();
    }

    private void setSadSongList() {
        if(arrayAdapter!=null){
            arrayAdapter.clear();
        }

        if(jcAudios!=null) {
            jcAudios.clear();
        }
        jcAudios = new ArrayList<>();
        jcAudios.add(JcAudio.createFromURL("Sad 1","https://firebasestorage.googleapis.com/v0/b/moodey-music.appspot.com/o/jag%20soona%20lage.mp3?alt=media&token=23158f8f-f376-4244-a09d-a85500675ebb"));
        jcAudios.add(JcAudio.createFromURL("Sad 2", "https://firebasestorage.googleapis.com/v0/b/moodey-music.appspot.com/o/tujhe%20bhula%20diya.mp3?alt=media&token=b9063733-08fa-4134-adc3-cdba117e4ad0"));
        jcAudios.add(JcAudio.createFromURL("Sad 3", "https://firebasestorage.googleapis.com/v0/b/moodey-music.appspot.com/o/y2mate.com%20-%20Arijit%20Singh%20Pachtaoge%20%20Vicky%20Kaushal%20Nora%20Fatehi%20Jaani%20B%20Praak%20Arvindr%20Khaira%20%20Bhushan%20Kumar.mp3?alt=media&token=aa5ed7e6-561a-4244-add7-b53e3b60d6fe"));
        jcAudios.add(JcAudio.createFromURL("Sad 4", "https://firebasestorage.googleapis.com/v0/b/moodey-music.appspot.com/o/y2mate.com%20-%20Atif%20A%20Dekhte%20Dekhte%20Lyrical%20%20Batti%20Gul%20Meter%20Chalu%20%20Shahid%20K%20Shraddha%20%20Nusrat%20Saab%20Rochak%20Manoj.mp3?alt=media&token=d19b5985-3231-4c42-aa59-fdaab3f27f53"));
        jcAudios.add(JcAudio.createFromURL("Sad 5", "https://firebasestorage.googleapis.com/v0/b/moodey-music.appspot.com/o/y2mate.com%20-%20Baarish%20%20Full%20Video%20%20Half%20Girlfriend%20%20Arjun%20Kapoor%20%20Shraddha%20Kapoor%20Ash%20King%20%20Sashaa%20%20Tanishk.mp3?alt=media&token=584c3202-942e-45b6-91a1-fb799143ca3a"));
        jcAudios.add(JcAudio.createFromURL("Sad 6", "https://firebasestorage.googleapis.com/v0/b/moodey-music.appspot.com/o/y2mate.com%20-%20FILHALL%20%20Akshay%20Kumar%20Ft%20Nupur%20Sanon%20%20BPraak%20%20Jaani%20%20Arvindr%20Khaira%20%20Ammy%20Virk%20%20Official%20Video.mp3?alt=media&token=9491226b-5e77-479d-825d-0c6b707d3969"));
        jcAudios.add(JcAudio.createFromURL("Sad 7", "https://firebasestorage.googleapis.com/v0/b/moodey-music.appspot.com/o/y2mate.com%20-%20Lyrical%20Jag%20Soona%20Soona%20Lage%20%20Om%20Shanti%20Om%20%20Shahrukh%20Khan%20Deepika%20Padukon.mp3?alt=media&token=d3aed940-82ec-4012-8c29-caa631684bbe"));
        jcAudios.add(JcAudio.createFromURL("Sad 8","https://firebasestorage.googleapis.com/v0/b/moodey-music.appspot.com/o/y2mate.com%20-%20Qismat%20Full%20Video%20%20Ammy%20Virk%20%20Sargun%20Mehta%20%20Jaani%20%20B%20Praak%20%20Arvindr%20Khaira%20%20Punjabi%20Songs.mp3?alt=media&token=93dfc07a-ef2f-4d79-9ae4-b51b0d7f8e72" ));
        jcAudios.add(JcAudio.createFromURL("Sad 9","https://firebasestorage.googleapis.com/v0/b/moodey-music.appspot.com/o/Yeh%20Duniya%20yeh%20mehfil%20mere%20kaam%20ki%20nahi.mp3?alt=media&token=ce6c8ec0-796d-4f99-be6c-4434639b424c" ));


        arrSadSongName.add(0,"Sad 1");
        arrSadSongName.add(0,"Sad 2");
        arrSadSongName.add(0,"Sad 3");
        arrSadSongName.add(0,"Sad 4");
        arrSadSongName.add(0,"Sad 5");
        arrSadSongName.add(0,"Sad 6");
        arrSadSongName.add(0,"Sad 7");
        arrSadSongName.add(0,"Sad 8");
        arrSadSongName.add(0,"Sad 9");

        arrayAdapter =  new ArrayAdapter<String>(this, R.layout.list_item_layout, arrSadSongName);


    }

    private void setHappySongList(){
        if(arrayAdapter!=null){
            arrayAdapter.clear();
        }
        if(jcAudios!=null) {
            jcAudios.clear();
        }
        jcAudios = new ArrayList<>();
        jcAudios.add(JcAudio.createFromURL("Happy 1", "https://firebasestorage.googleapis.com/v0/b/moodey-music.appspot.com/o/y2mate.com%20-%20Chhote%20Chhote%20Peg%20Video%20%20Yo%20Yo%20Honey%20Singh%20%20Neha%20Kakkar%20%20Navraj%20Hans%20%20Sonu%20Ke%20Titu%20Ki%20Sweety.mp3?alt=media&token=2fe3c966-15f0-48d8-bc2b-9e54d582ffca"));
        jcAudios.add(JcAudio.createFromURL("Happy 2", "https://firebasestorage.googleapis.com/v0/b/moodey-music.appspot.com/o/y2mate.com%20-%20Harrdy%20Sandhu%20%20Kya%20Baat%20Ay%20%20Jaani%20%20B%20Praak%20%20%20Arvindr%20Khaira%20%20Official%20Music%20Video.mp3?alt=media&token=19237068-5eb2-4403-96fd-cc9a15c7f3b2"));
        jcAudios.add(JcAudio.createFromURL("Happy 3", "https://firebasestorage.googleapis.com/v0/b/moodey-music.appspot.com/o/y2mate.com%20-%20Kya%20Karu%20Full%20Song%20Millind%20Gaba%20Feat%20Ashnoor%20K%20%20Parampara%20T%20%20Asli%20Gold%20%20Shabby%20%20Bhushan%20Kumar.mp3?alt=media&token=dd12587c-9fc4-4526-a644-2ff59a482ef3"));
        jcAudios.add(JcAudio.createFromURL("Happy 4", "https://firebasestorage.googleapis.com/v0/b/moodey-music.appspot.com/o/y2mate.com%20-%20Lungi%20Dance%20Chennai%20Express%20New%20Video%20Feat%20Honey%20Singh%20Shahrukh%20Khan%20Deepika.mp3?alt=media&token=32331ed9-6207-49b7-9b4b-953e05fe2d23"));
        jcAudios.add(JcAudio.createFromURL("Happy 5", "https://firebasestorage.googleapis.com/v0/b/moodey-music.appspot.com/o/y2mate.com%20-%20One%20Bottle%20Down%20FULL%20VIDEO%20SONG%20%20Yo%20Yo%20Honey%20Singh%20%20TSERIES.mp3?alt=media&token=1c2394d0-80f0-4743-8b3b-7235bf78c285"));
        jcAudios.add(JcAudio.createFromURL("Happy 6", "https://firebasestorage.googleapis.com/v0/b/moodey-music.appspot.com/o/y2mate.com%20-%20One%20Bottle%20Down%20FULL%20VIDEO%20SONG%20%20Yo%20Yo%20Honey%20Singh%20%20TSERIES.mp3?alt=media&token=1c2394d0-80f0-4743-8b3b-7235bf78c285"));
        jcAudios.add(JcAudio.createFromURL("Happy 7",  "https://firebasestorage.googleapis.com/v0/b/moodey-music.appspot.com/o/y2mate.com%20-%20Party%20With%20The%20Bhoothnath%20Song%20Official%20%20Bhoothnath%20Returns%20%20Amitabh%20Bachchan%20Yo%20Yo%20Honey%20Singh.mp3?alt=media&token=4116d042-197f-4463-a02e-a6a5ee86c95e"));

        arrHappySongName.add(0,"Happy 1");
        arrHappySongName.add(0,"Happy 2");
        arrHappySongName.add(0,"Happy 3");
        arrHappySongName.add(0,"Happy 4");
        arrHappySongName.add(0,"Happy 5");
        arrHappySongName.add(0,"Happy 6");
        arrHappySongName.add(0,"Happy 7");

        arrayAdapter =  new ArrayAdapter<String>(this, R.layout.list_item_layout,   arrHappySongName);

    }

    private void startTheMusic() {

        if(!isSad){
            setHappySongList();
        } else {
            setSadSongList();
        }

        jcplayer.initPlaylist(jcAudios,null);
        lv.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();

        // Start The music

        jcplayer.playAudio(jcAudios.get(new Random().nextInt(jcAudios.size())));
        jcplayer.setVisibility(View.VISIBLE);
        jcplayer.createNotification();
    }

    private void init() {
        expressionBackgroundRL = findViewById(R.id.expression_background_ll);
        emojiPlaceholderIV = findViewById(R.id.emoji_placeholder_iv);

        jcplayer = (JcPlayerView) findViewById(R.id.jcplayer);
        lv = (ListView) findViewById((R.id.myListView));

        //list view onclick listener for each song
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                jcplayer.playAudio(jcAudios.get(position));
                jcplayer.setVisibility(View.VISIBLE);
               //jcplayer.createNotification();
            }
        });

    }

    private void setMood() {

        int emoji = R.drawable.happy;
        int background = R.drawable.happy_bg;
        int backgroundOfTV = R.drawable.happy_rounded_corners;

        TextView moodTextView = findViewById(R.id.moodTV);
        if (AppController.currentMood == MoodConstants.MOOD.SAD) {
            emoji = R.drawable.sad;
            background = R.drawable.sad_bg;
            musicUrl = AppController.getSadSong();
            moodTextView.setText("Sad");
            isSad=true;
        } else if (AppController.currentMood == MoodConstants.MOOD.HAPPY) {
            emoji = R.drawable.happy;
            background = R.drawable.happy_bg;
            musicUrl = AppController.getHappySong();
            moodTextView.setText("Happy");
            isSad = false;
        } else if (AppController.currentMood == MoodConstants.MOOD.MIDDLE) {
            emoji = R.drawable.calm;
            background = R.drawable.calm_bg;
            musicUrl = AppController.getHappySong();
            moodTextView.setText("Calm");
            isSad = false;
        }

       // emojiPlaceholderIV.setImageResource(emoji);
     //   expressionBackgroundRL.setBackground(getResources().getDrawable(background));
       // setRippleEffect();

        jcplayer.setBackground(getResources().getDrawable(background));

    }

    private void setRippleEffect() {
        runnable = new Runnable() {
            @Override
            public void run() {
                forceRippleAnimation(expressionBackgroundRL);
                handler.postDelayed(this, 500);
            }
        };
        handler = new Handler();
        handler.postDelayed(runnable, 500);
    }

    protected void forceRippleAnimation(View view) {
        Drawable background = view.getBackground();

        if (Build.VERSION.SDK_INT >= 21 && background instanceof RippleDrawable) {
            final RippleDrawable rippleDrawable = (RippleDrawable) background;

            rippleDrawable.setState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled});

            Handler handler = new Handler();

            handler.postDelayed(() -> rippleDrawable.setState(new int[]{}), 200);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       // destroyMediaPlayer();
    }

}