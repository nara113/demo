package we.demo.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import we.demo.domain.Form;
import we.demo.domain.Result;
import we.demo.domain.Type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class FormServiceTest {
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private FormService formService;

    @Test
    void getResult() {
        // given
        String html = "<body>hello 4322 <br></br> 1987<p>hello</p> hi 9876</body>";

        Form form = Form.builder()
                .url("url")
                .type(Type.EXCLUDE_HTML_TAG)
                .bundleUnit(10)
                .build();

        given(restTemplate.getForObject(form.getUrl(), String.class))
                .willReturn(html);

        // when
        Result result = formService.getResult(form);

        // then
        then(restTemplate).should(times(1)).getForObject(form.getUrl(), String.class);
        assertThat(result.getQuotient()).isEqualTo("e1e2h2h3h4i6l7l7l8l8");
        assertThat(result.getRemainder()).isEqualTo("o9o9");
    }

    @DisplayName("몫이 제로")
    @Test
    void getResult_quotient_zero() {
        // given
        String html = "<body>hello 4322 <br></br> 1987<p>hello</p> hi 9876</body>";

        Form form = Form.builder()
                .url("url")
                .type(Type.EXCLUDE_HTML_TAG)
                .bundleUnit(100)
                .build();

        given(restTemplate.getForObject(form.getUrl(), String.class))
                .willReturn(html);

        // when
        Result result = formService.getResult(form);

        // then
        then(restTemplate).should(times(1)).getForObject(form.getUrl(), String.class);
        assertThat(result.getQuotient()).isEqualTo("");
        assertThat(result.getRemainder()).isEqualTo("e1e2h2h3h4i6l7l7l8l8o9o9");
    }

    @DisplayName("나머지가 제로")
    @Test
    void getResult_remainder_zero() {
        // given
        String html = "<body>hello 4322 <br></br> 1987<p>hello</p> hi 9876</body>";

        Form form = Form.builder()
                .url("url")
                .type(Type.EXCLUDE_HTML_TAG)
                .bundleUnit(1)
                .build();

        given(restTemplate.getForObject(form.getUrl(), String.class))
                .willReturn(html);

        // when
        Result result = formService.getResult(form);

        // then
        then(restTemplate).should(times(1)).getForObject(form.getUrl(), String.class);
        assertThat(result.getQuotient()).isEqualTo("e1e2h2h3h4i6l7l7l8l8o9o9");
        assertThat(result.getRemainder()).isEqualTo("");
    }
}