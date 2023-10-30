import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
public class DogImageCarousel extends JFrame {
    private ArrayList<String> imageUrls;
    private int currentIndex = 0;
    private ImagePanel imagePanel;
    private Timer timer;

    public DogImageCarousel(ArrayList<String> urls) {
        super();
        imageUrls = urls;

        updateImagePanel();

        startImageCarousel();

        setTitle("Dog Image Carousel");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void updateImagePanel() {
        if (imageUrls.size() > 0) {
            try {
                URL imageUrl = new URL(imageUrls.get(currentIndex));
                imagePanel = new ImagePanel(imageUrl);

                setContentPane(imagePanel);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void startImageCarousel() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (currentIndex < imageUrls.size() - 1) {
                    currentIndex++;
                } else {
                    currentIndex = 0;
                }
                updateImagePanel();
                revalidate();
                repaint();
            }
        }, 0, 15000);
    }
}