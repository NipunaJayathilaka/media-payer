package lk.ijse.dep10;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.time.format.DateTimeFormatter;

public class AppInitializer extends Application {
    private boolean isAudioPlaying = false;
    private MediaPlayer mediaPlayer;
    private Timeline timeline;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        mainScene();
//        primaryStage.centerOnScreen();
//        primaryStage.show();

    }
    private void mainScene(){
        Button btnOpen = new Button("Open");
        //Label lblLocation = new Label("File path: ");
        Label lblMedia = new Label("Media player");
        ImageView img1  = new ImageView();
        ImageView img2  = new ImageView();
        ImageView img3  = new ImageView();
        ImageView img4  = new ImageView();
        ImageView img5 = new ImageView();
        Slider slider = new Slider();


        AnchorPane aPane = new AnchorPane(btnOpen);
        btnOpen.setAlignment(Pos.TOP_LEFT);

        HBox hBoxlbl = new HBox(lblMedia);
        HBox hBox = new HBox(10,img1,img2,img3,img4,slider);
        VBox root = new VBox(aPane,hBoxlbl,img5,hBox);

        Image imgPlay = new Image((this.getClass().getResource("/img/play.png")).toString());
        Image imgPause = new Image((this.getClass().getResource("/img/pause.png")).toString());
        Image imgStop = new Image((this.getClass().getResource("/img/stop.png")).toString());
        Image imgLoop = new Image((this.getClass().getResource("/img/reload.png")).toString());
        Image imgMute = new Image((this.getClass().getResource("/img/mute.png")).toString());
        Image imgUnMute = new Image((this.getClass().getResource("/img/volume.png")).toString());
        Image imgLogo = new Image(this.getClass().getResource("/img/music.png").toString());

        img1.setImage(imgPlay);
       // img.setImage(imgPause);
        img2.setImage(imgStop);
        img3.setImage(imgLoop);
        img4.setImage(imgUnMute);
        //img.setImage(imgVolume);
        img5.setImage(imgLogo);

        img1.setFitHeight(20);
        img1.setFitWidth(20);
        img1.setCursor(Cursor.HAND);
        img2.setFitHeight(20);
        img2.setFitWidth(20);
        img2.setCursor(Cursor.HAND);
        img3.setFitHeight(20);
        img3.setFitWidth(20);
        img3.setCursor(Cursor.HAND);
        img4.setFitHeight(20);
        img4.setFitWidth(20);
        img4.setCursor(Cursor.HAND);
        img5.setFitWidth(200);
        img5.setFitHeight(200);
        slider.setLayoutY(50);

        root.setSpacing(30);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);

        img1.setOnMouseEntered(event -> img1.setOpacity(0.8));
        img1.setOnMouseExited(event -> img1.setOpacity(1));
        img1.setOnMousePressed(event -> img1.setEffect(new InnerShadow(10, Color.BLACK)));
        img1.setOnMouseReleased(event -> {
            img1.setEffect(null);

            if (isAudioPlaying){
                //lblStatus.setText("Stopped!");
                img1.setImage(imgPlay);
                isAudioPlaying = false;
                mediaPlayer.pause();
            }else{
                //lblStatus.setText("Playing...");
                img1.setImage(imgPause);
                isAudioPlaying = true;
                mediaPlayer.play();

                KeyFrame key1 = new KeyFrame(Duration.millis(100),actionEvent -> {
                    img5.setScaleX(0.9);
                    img5.setScaleY(0.9);
                });
                KeyFrame key2 = new KeyFrame(Duration.millis(200),actionEvent -> {
                    img5.setScaleX(1);
                    img5.setScaleY(1);
                });
                Timeline timeline = new Timeline(key1,key2);
                timeline.setCycleCount(Animation.INDEFINITE);
                timeline.play();
            }
        });

        img2.setOnMouseEntered(event -> img2.setOpacity(0.8));
        img2.setOnMouseExited(event -> img2.setOpacity(1));
        img2.setOnMousePressed(event -> img2.setEffect(new InnerShadow(10, Color.BLACK)));
        img2.setOnMouseReleased(event -> {
            img2.setEffect(null);

            if (isAudioPlaying){
                //lblStatus.setText("Stopped!");
                img2.setImage(imgStop);
                isAudioPlaying = false;
                mediaPlayer.stop();
                //timeline.stop();
            }else{
                //lblStatus.setText("Playing...");
                img2.setImage(imgStop);
                img1.setImage(imgPlay);
                isAudioPlaying = true;

            }
        });
        img3.setOnMouseEntered(event -> img3.setOpacity(0.8));
        img3.setOnMouseExited(event -> img3.setOpacity(1));
        img3.setOnMousePressed(event -> img3.setEffect(new InnerShadow(10, Color.BLACK)));
        img3.setOnMouseReleased(event -> {
            img3.setEffect(null);

            if (isAudioPlaying){
                //lblStatus.setText("Stopped!");
                img3.setImage(imgLoop);
                isAudioPlaying = false;


            }else{
                //lblStatus.setText("Playing...");
                img3.setImage(imgLoop);
                isAudioPlaying = true;
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            }
        });
        img4.setOnMouseEntered(event -> img4.setOpacity(0.8));
        img4.setOnMouseExited(event -> img4.setOpacity(1));
        img4.setOnMousePressed(event -> img4.setEffect(new InnerShadow(10, Color.BLACK)));
        img4.setOnMouseReleased(event -> {
            img4.setEffect(null);

            if (isAudioPlaying){
                //lblStatus.setText("Stopped!");
                img4.setImage(imgMute);
                isAudioPlaying = false;
                mediaPlayer.setMute(true);
            }else{
                //lblStatus.setText("Playing...");
                img4.setImage(imgUnMute);
                isAudioPlaying = true;
                mediaPlayer.setMute(false);
            }
        });



        btnOpen.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();

            fileChooser.setTitle("Open an audio file");
            File audioFile = fileChooser.showOpenDialog(null);

            if (audioFile != null){
                Media media = new Media(audioFile.toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                lblMedia.setText(String.valueOf(audioFile));

//                setMute, isMute
//                setVolume, getVolum
//                pause
//                setCycleCount
            }else{
                mediaPlayer = null;
            }

//            TranslateTransition transition = new TranslateTransition(Duration.millis(100),lblMedia);
//            transition.setFromX(0);
//            transition.setToX(200);
//            transition.play();
        });

        slider.setShowTickMarks(true);
        slider.setBackground(Background.fill(Color.BLACK));
        slider.valueProperty().addListener((oldNumber,newNumber,currentNumber)->{
            if(mediaPlayer !=null){
                mediaPlayer.setVolume(currentNumber.doubleValue()/100);
            }
        });

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setTitle("Media Player");
        stage.centerOnScreen();
        stage.show();


    }
}
