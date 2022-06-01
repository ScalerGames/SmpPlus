package Dev.ScalerGames.SmpPlus.Utils;

import org.apache.commons.lang.StringUtils;

import java.util.Arrays;

public class Tools {

    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static String join(String[] args, Integer from) {
        return StringUtils.join(Arrays.copyOfRange(args, from, args.length), " ");
    }

}
