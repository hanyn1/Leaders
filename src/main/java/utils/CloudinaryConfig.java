package utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class CloudinaryConfig {
    private  static final String CLOUD_NAME ="dpm4fmh5y";
    private  static  final String API_KEY="572283849116667";
    private static final String API_SECRET="oZ-wDMqrhhyNqke8TAado6ppPXc";

    private static Cloudinary cloudinary;
    static  {
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", CLOUD_NAME,
                "api_key", API_KEY,
                "api_secret", API_SECRET,
                "secure", true
        ));
    }
    public static Cloudinary getCloudinary(){
        return cloudinary;
    }
}
