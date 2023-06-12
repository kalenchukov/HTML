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

package dev.kalenchukov.html.resources;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

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

		assertEquals("comment", actualGroup);
	}

	/**
	 * Проверка метода {@link Regexp#getPattern()}.
	 */
	@Test
	public void getPattern()
	{
		Regexp regexp = Regexp.COMMENT;

		String actualPattern = regexp.getPattern();

		assertFalse(actualPattern.isEmpty());
	}

	/**
	 * Класс проверки регулярного выражения констант перечисления {@link Regexp}.
	 *
	 * @author Алексей Каленчуков
	 */
	@Nested
	public class PatternTest
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
			assertTrue(matcher.matches());

			String actualGroup1 = matcher.group("comment");
			String actualGroup2 = matcher.group("value");

			assertEquals("<!-- Комментарий -->", actualGroup1);
			assertEquals(" Комментарий ", actualGroup2);
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
			assertTrue(matcher.matches());

			String actualGroup1 = matcher.group("entity");
			String actualGroup2 = matcher.group("name");

			assertEquals("&DownArrowBar;", actualGroup1);
			assertEquals("DownArrowBar", actualGroup2);
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
			assertTrue(matcher.matches());

			String actualGroup1 = matcher.group("entity");
			String actualGroup2 = matcher.group("numeric");
			String actualGroup3 = matcher.group("numericLeast");

			assertEquals("&#0010590;", actualGroup1);
			assertEquals("0010590", actualGroup2);
			assertEquals("10590", actualGroup3);
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
			assertTrue(matcher.matches());

			String actualGroup1 = matcher.group("entity");
			String actualGroup2 = matcher.group("unicode");
			String actualGroup3 = matcher.group("unicodeLeast");

			assertEquals("&#X000154;", actualGroup1);
			assertEquals("000154", actualGroup2);
			assertEquals("154", actualGroup3);
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
			assertTrue(matcher.matches());

			String actualGroup1 = matcher.group("doctype");
			String actualGroup2 = matcher.group("rootElement");
			String actualGroup3 = matcher.group("public");
			String actualGroup4 = matcher.group("dtd");
			String actualGroup5 = matcher.group("registration");
			String actualGroup6 = matcher.group("organization");
			String actualGroup7 = matcher.group("documentType");
			String actualGroup8 = matcher.group("language");
			String actualGroup9 = matcher.group("url");

			assertEquals(
				"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">",
				actualGroup1
			);
			assertEquals("HTML", actualGroup2);
			assertEquals("PUBLIC", actualGroup3);
			assertEquals("-//W3C//DTD HTML 4.01//EN", actualGroup4);
			assertEquals("-", actualGroup5);
			assertEquals("W3C", actualGroup6);
			assertEquals("DTD HTML 4.01", actualGroup7);
			assertEquals("EN", actualGroup8);
			assertEquals("http://www.w3.org/TR/html4/strict.dtd", actualGroup9);
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
			assertTrue(matcher.matches());

			String actualGroup1 = matcher.group("tag");
			String actualGroup2 = matcher.group("name");

			assertEquals("</form >", actualGroup1);
			assertEquals("form", actualGroup2);
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
			assertTrue(matcher.matches());

			String actualGroup1 = matcher.group("tag");
			String actualGroup2 = matcher.group("name");
			String actualGroup3 = matcher.group("params");

			assertEquals(
				"<input name=viewport content=\"width=device-width, initial-scale=1, user-scalable=0\"/>",
				actualGroup1
			);
			assertEquals("input", actualGroup2);
			assertEquals(
				" name=viewport content=\"width=device-width, initial-scale=1, user-scalable=0\"",
				actualGroup3
			);
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
			assertTrue(matcher.matches());

			String actualGroup1 = matcher.group("tag");
			String actualGroup2 = matcher.group("name");
			String actualGroup3 = matcher.group("params");

			assertEquals(
				"<meta name=viewport content=\"width=device-width, initial-scale=1, user-scalable=0\">",
				actualGroup1
			);
			assertEquals("meta", actualGroup2);
			assertEquals(
				" name=viewport content=\"width=device-width, initial-scale=1, user-scalable=0\"",
				actualGroup3
			);
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
			assertTrue(matcher.matches());

			String actualGroup1 = matcher.group("cdata");
			String actualGroup2 = matcher.group("value");

			assertEquals("<![CDATA[ Текст ]]>", actualGroup1);
			assertEquals(" Текст ", actualGroup2);
		}
	}
}