package Dev.ScalerGames.SmpPlus.Utils;

import org.apache.commons.lang.StringUtils;

import java.util.Arrays;

public class StringJoin {

    public static String arrays(String[] args, Integer from) {

        String arguments = StringUtils.join(Arrays.copyOfRange(args, from, args.length), " ");
        return arguments;
    }

}
