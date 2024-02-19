package net.onestorm.library.action;

import java.util.regex.Pattern;

public interface ReplacePatternAction {

    void replace(Pattern pattern, String value);

}
