package Controllers;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.IOException;
import java.nio.file.Paths;

public class GenerateQRCode {
    public static void main(String[] args) {
        String data = "11";
        String path = "C:/Users/Hejer Zouaoui/OneDrive/Bureau/QR.jpg";
        int width = 500;
        int height = 500;

        try {
            BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, width, height);
            MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(path));
            System.out.println("QR code successfully generated");
        } catch (WriterException | IOException e) {
            System.err.println("Error generating QR code: " + e.getMessage());
        }
    }
}

