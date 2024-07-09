package Controllers;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCode {

    // static function that creates QR Code
    public static void generateQRcode(String data, String path, String charset, Map<EncodeHintType, ErrorCorrectionLevel> map, int h, int w) throws WriterException, IOException {
        BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, w, h);
        Path outputPath = Paths.get(path);
        MatrixToImageWriter.writeToPath(matrix, "PNG", outputPath);
    }

    // main() method
    public static void main(String args[]) throws WriterException, IOException {
        String str = "11";
        String path = "C:/Users/Hejer Zouaoui/OneDrive/Bureau/QRCode.png"; // Specify full path including file name
        String charset = "UTF-8";
        Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<>();
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        generateQRcode(str, path, charset, hashMap, 200, 200);
        System.out.println("QR Code created successfully.");
    }
}

