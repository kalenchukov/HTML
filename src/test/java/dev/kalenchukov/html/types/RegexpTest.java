/*
 * Copyright © 2022-2023 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.kalenchukov.html.types;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Класс проверки констант и методов перечисления {@link Regexp}.
 *
 * @author Алексей Каленчуков
 */
public class RegexpTest
{
	/**
	 * Проверка метода {@link Regexp#getGroup()}.
	 */
	@Test
	public void getGroup()
	{
		Regexp regexp = Regexp.COMMENT;

		String actualGroup = regexp.getGroup();

		assertThat(actualGroup).isEqualTo("comment");
	}

	/**
	 * Проверка метода {@link Regexp#getPattern()}.
	 */
	@Test
	public void getPattern()
	{
		Regexp regexp = Regexp.COMMENT;

		String actualPattern = regexp.getPattern();

		assertThat(actualPattern).isNotEmpty();
	}

	/**
	 * Класс проверки регулярного выражения констант перечисления {@link Regexp}.
	 *
	 * @author Алексей Каленчуков
	 */
	@Nested
	public class PatternRegexp
	{
		/**
		 * Проверка регулярного выражения константы {@link Regexp#COMMENT}.
		 */
		@Test
		public void comment()
		{
			String value = "<!-- Комментарий -->";
			Pattern pattern = Pattern.compile(
				Regexp.COMMENT.getPattern(),
				Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
			);
			Matcher matcher = pattern.matcher(value);
			assertThat(matcher.matches()).isTrue();

			String actualGroup1 = matcher.group("comment");
			String actualGroup2 = matcher.group("value");

			assertThat(actualGroup1).isEqualTo("<!-- Комментарий -->");
			assertThat(actualGroup2).isEqualTo(" Комментарий ");
		}

		/**
		 * Проверка регулярного выражения константы {@link Regexp#ENTITY_NAME}.
		 */
		@Test
		public void entityName()
		{
			String value = "&DownArrowBar;";
			Pattern pattern = Pattern.compile(
				Regexp.ENTITY_NAME.getPattern(),
				Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
			);
			Matcher matcher = pattern.matcher(value);
			assertThat(matcher.matches()).isTrue();

			String actualGroup1 = matcher.group("entity");
			String actualGroup2 = matcher.group("name");

			assertThat(actualGroup1).isEqualTo("&DownArrowBar;");
			assertThat(actualGroup2).isEqualTo("DownArrowBar");
		}

		/**
		 * Проверка регулярного выражения константы {@link Regexp#ENTITY_NUMERIC}.
		 */
		@Test
		public void entityNumeric()
		{
			String value = "&#0010590;";
			Pattern pattern = Pattern.compile(
				Regexp.ENTITY_NUMERIC.getPattern(),
				Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
			);
			Matcher matcher = pattern.matcher(value);
			assertThat(matcher.matches()).isTrue();

			String actualGroup1 = matcher.group("entity");
			String actualGroup2 = matcher.group("numeric");
			String actualGroup3 = matcher.group("numericLeast");

			assertThat(actualGroup1).isEqualTo("&#0010590;");
			assertThat(actualGroup2).isEqualTo("0010590");
			assertThat(actualGroup3).isEqualTo("10590");
		}

		/**
		 * Проверка регулярного выражения константы {@link Regexp#ENTITY_UNICODE}.
		 */
		@Test
		public void entityUnicode()
		{
			String value = "&#X000154;";
			Pattern pattern = Pattern.compile(
				Regexp.ENTITY_UNICODE.getPattern(),
				Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
			);
			Matcher matcher = pattern.matcher(value);
			assertThat(matcher.matches()).isTrue();

			String actualGroup1 = matcher.group("entity");
			String actualGroup2 = matcher.group("unicode");
			String actualGroup3 = matcher.group("unicodeLeast");

			assertThat(actualGroup1).isEqualTo("&#X000154;");
			assertThat(actualGroup2).isEqualTo("000154");
			assertThat(actualGroup3).isEqualTo("154");
		}

		/**
		 * Проверка регулярного выражения константы {@link Regexp#DOCTYPE}.
		 */
		@Test
		public void doctype()
		{
			String value = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">";
			Pattern pattern = Pattern.compile(
				Regexp.DOCTYPE.getPattern(),
				Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
			);
			Matcher matcher = pattern.matcher(value);
			assertThat(matcher.matches()).isTrue();

			String actualGroup1 = matcher.group("doctype");
			String actualGroup2 = matcher.group("rootElement");
			String actualGroup3 = matcher.group("public");
			String actualGroup4 = matcher.group("dtd");
			String actualGroup5 = matcher.group("registration");
			String actualGroup6 = matcher.group("organization");
			String actualGroup7 = matcher.group("documentType");
			String actualGroup8 = matcher.group("language");
			String actualGroup9 = matcher.group("url");

			assertThat(actualGroup1).isEqualTo("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">");
			assertThat(actualGroup2).isEqualTo("HTML");
			assertThat(actualGroup3).isEqualTo("PUBLIC");
			assertThat(actualGroup4).isEqualTo("-//W3C//DTD HTML 4.01//EN");
			assertThat(actualGroup5).isEqualTo("-");
			assertThat(actualGroup6).isEqualTo("W3C");
			assertThat(actualGroup7).isEqualTo("DTD HTML 4.01");
			assertThat(actualGroup8).isEqualTo("EN");
			assertThat(actualGroup9).isEqualTo("http://www.w3.org/TR/html4/strict.dtd");
		}

		/**
		 * Проверка регулярного выражения константы {@link Regexp#CLOSE_TAG}.
		 */
		@Test
		public void closeTag()
		{
			String value = "</form >";
			Pattern pattern = Pattern.compile(
				Regexp.CLOSE_TAG.getPattern(),
				Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
			);
			Matcher matcher = pattern.matcher(value);
			assertThat(matcher.matches()).isTrue();

			String actualGroup1 = matcher.group("tag");
			String actualGroup2 = matcher.group("name");

			assertThat(actualGroup1).isEqualTo("</form >");
			assertThat(actualGroup2).isEqualTo("form");
		}

		/**
		 * Проверка регулярного выражения константы {@link Regexp#SELF_CLOSING_TAG}.
		 */
		@Test
		public void selfClosingTag()
		{
			String value = "<input name=viewport content=\"width=device-width, initial-scale=1, user-scalable=0\"/>";

			Pattern pattern = Pattern.compile(
				Regexp.SELF_CLOSING_TAG.getPattern(),
				Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
			);
			Matcher matcher = pattern.matcher(value);
			assertThat(matcher.matches()).isTrue();

			String actualGroup1 = matcher.group("tag");
			String actualGroup2 = matcher.group("name");
			String actualGroup3 = matcher.group("params");

			assertThat(actualGroup1).isEqualTo("<input name=viewport content=\"width=device-width, initial-scale=1, user-scalable=0\"/>");
			assertThat(actualGroup2).isEqualTo("input");
			assertThat(actualGroup3).isEqualTo(" name=viewport content=\"width=device-width, initial-scale=1, user-scalable=0\"");
		}

		/**
		 * Проверка регулярного выражения константы {@link Regexp#OPEN_TAG}.
		 */
		@Test
		public void openTag()
		{
			String string = "<meta name=viewport content=\"width=device-width, initial-scale=1, user-scalable=0\">";
			Pattern pattern = Pattern.compile(
				Regexp.OPEN_TAG.getPattern(),
				Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
			);
			Matcher matcher = pattern.matcher(string);
			assertThat(matcher.matches()).isTrue();

			String actualGroup1 = matcher.group("tag");
			String actualGroup2 = matcher.group("name");
			String actualGroup3 = matcher.group("params");

			assertThat(actualGroup1).isEqualTo("<meta name=viewport content=\"width=device-width, initial-scale=1, user-scalable=0\">");
			assertThat(actualGroup2).isEqualTo("meta");
			assertThat(actualGroup3).isEqualTo(" name=viewport content=\"width=device-width, initial-scale=1, user-scalable=0\"");
		}

		/**
		 * Проверка регулярного выражения константы {@link Regexp#CDATA}.
		 */
		@Test
		public void cData()
		{
			String string = "<![CDATA[ Текст ]]>";
			Pattern pattern = Pattern.compile(
				Regexp.CDATA.getPattern(),
				Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
			);
			Matcher matcher = pattern.matcher(string);
			assertThat(matcher.matches()).isTrue();

			String actualGroup1 = matcher.group("cdata");
			String actualGroup2 = matcher.group("value");

			assertThat(actualGroup1).isEqualTo("<![CDATA[ Текст ]]>");
			assertThat(actualGroup2).isEqualTo(" Текст ");
		}
	}
}