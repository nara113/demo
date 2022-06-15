package we.demo.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import we.demo.domain.Form;
import we.demo.domain.Result;
import we.demo.domain.Type;
import we.demo.util.StringUtil;

@RequiredArgsConstructor
@Service
public class FormService {

    private final RestTemplate restTemplate;

    public Result getResult(Form form) {
        String html;

        try {
            html = restTemplate.getForObject(form.getUrl(), String.class);

        } catch (Exception ex) {
            throw new IllegalArgumentException("URL is invalid. url : " + form.getUrl());
        }

        if (form.getType() == Type.EXCLUDE_HTML_TAG) {
            html = StringUtil.removeTag(html);
        }

        String sortedDigits = StringUtil.sortDigits(StringUtil.getDigits(html));
        String sortedAlphabets = StringUtil.sortAlphabets(StringUtil.getAlphabets(html));

        String mixString = StringUtil.mixString(sortedDigits, sortedAlphabets);

        int mixLength = mixString.length();
        int bundleUnit = form.getBundleUnit();

        int quotient = mixLength / bundleUnit;
        int remainder = mixLength % bundleUnit;

        return Result.builder()
                .quotient(mixString.substring(0, quotient * bundleUnit))
                .remainder(mixString.substring(mixLength - remainder))
                .build();
    }
}
