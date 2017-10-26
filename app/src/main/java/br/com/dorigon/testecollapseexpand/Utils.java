package br.com.dorigon.testecollapseexpand;

import android.content.res.Resources;

/**
 * @author Luiz Otávio Dorigon <luiz@datajuri.com.br> on 26/10/17.
 */

public final class Utils {

    private Utils() {}

    public static int dpToPx(int px) {
        return (int) (px * Resources.getSystem().getDisplayMetrics().density);
    }
}
