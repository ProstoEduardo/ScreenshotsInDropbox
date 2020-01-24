import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main extends MyThread {
    public static void main(String[] args) throws AWTException, IOException {

     MyThread thread = new MyThread();
     thread.run();
   }
}

