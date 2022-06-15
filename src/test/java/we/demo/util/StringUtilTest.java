package we.demo.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringUtilTest {

    @Test
    void removeTag() {
        String html = "<div>4test**</div>\n<p>##hi321<br /><br />\n hello 98765</p>";
        String text = StringUtil.removeTag(html);

        assertThat(text).isEqualTo("4test**\n##hi321\n hello 98765");
    }

    @Test
    void getDigits() {
        String html = "<div>4test**</div>\n<p>##hi321<br /><br />\n hello 98765</p>";
        String text = StringUtil.getDigits(html);

        assertThat(text).isEqualTo("432198765");
    }

    @Test
    void getAlphabets() {
        String html = "<div>4test**</div>\n<p>##hi321<br /><br />\n hello 98765</p>";
        String text = StringUtil.getAlphabets(html);

        assertThat(text).isEqualTo("divtestdivphibrbrhellop");
    }

    @Test
    void sortDigits() {
        String digits = "432198765";
        String sortedDigits = StringUtil.sortDigits(digits);

        assertThat(sortedDigits).isEqualTo("123456789");
    }

    @Test
    void sortAlphabets() {
        String alphabets = "divtestdivphibrbrhellop";
        String sortedAlphabets = StringUtil.sortDigits(alphabets);

        assertThat(sortedAlphabets).isEqualTo("bbddeehhiiillopprrsttvv");
    }

    @Test
    void mixString() {
        String digits = "123456789";
        String alphabets = "bbddeehhiiillopprrsttvv";

        String mixString = StringUtil.mixString(digits, alphabets);
        assertThat(mixString).isEqualTo("b1b2d3d4e5e6h7h8i9iillopprrsttvv");
    }
}