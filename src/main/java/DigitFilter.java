import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

class DigitFilter extends DocumentFilter {

    private static String digits; // = "\\d+";

    DigitFilter(String digits) {
        DigitFilter.digits = digits;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {

        if (string.matches(digits)) {
                super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attrs) throws BadLocationException {
        if (string.matches(digits)) {
                super.replace(fb, offset, length, string, attrs);
        }
    }
}