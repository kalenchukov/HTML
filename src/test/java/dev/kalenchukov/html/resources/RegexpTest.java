/*
 * Copyright © 2023 Алексей Каленчуков
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

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class RegexpTest
{
	/**
	 * Проверка групп.
	 */
	@Test
	public void testRegexpComment()
	{
		String string = "<!-- Комментарий -->";

		Pattern pattern = Pattern.compile(
			Regexp.COMMENT.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(string);

		assertTrue(matcher.find());

		assertEquals("<!-- Комментарий -->", matcher.group(Regexp.COMMENT.getGroup()));
		assertEquals(" Комментарий ", matcher.group("value"));
	}

	/**
	 * Проверка групп.
	 */
	@Test
	public void testRegexpEntityName()
	{
		String string = "&DownArrowBar;";

		Pattern pattern = Pattern.compile(
			Regexp.ENTITY_NAME.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(string);

		assertTrue(matcher.find());

		assertEquals("&DownArrowBar;", matcher.group(Regexp.ENTITY_NAME.getGroup()));
		assertEquals("DownArrowBar", matcher.group("name"));
	}

	/**
	 * Проверка групп.
	 */
	@Test
	public void testRegexpEntityNumeric()
	{
		String string = "&#0010590;";

		Pattern pattern = Pattern.compile(
			Regexp.ENTITY_NUMERIC.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(string);

		assertTrue(matcher.find());

		assertEquals("&#0010590;", matcher.group(Regexp.ENTITY_NUMERIC.getGroup()));
		assertEquals("0010590", matcher.group("numeric"));
		assertEquals("10590", matcher.group("numericLeast"));
	}

	/**
	 * Проверка групп.
	 */
	@Test
	public void testRegexpEntityUnicode()
	{
		String string = "&#X000154;";

		Pattern pattern = Pattern.compile(
			Regexp.ENTITY_UNICODE.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(string);

		assertTrue(matcher.find());

		assertEquals("&#X000154;", matcher.group(Regexp.ENTITY_UNICODE.getGroup()));
		assertEquals("000154", matcher.group("unicode"));
		assertEquals("154", matcher.group("unicodeLeast"));
	}

	/**
	 * Проверка групп.
	 */
	@Test
	public void testRegexpDoctype()
	{
		String string = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">";

		Pattern pattern = Pattern.compile(
			Regexp.DOCTYPE.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(string);

		assertTrue(matcher.find());

		assertEquals(
			"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">",
			matcher.group(Regexp.DOCTYPE.getGroup())
		);
		assertEquals("HTML", matcher.group("rootElement"));
		assertEquals("PUBLIC", matcher.group("public"));
		assertEquals("-//W3C//DTD HTML 4.01//EN", matcher.group("dtd"));
		assertEquals("-", matcher.group("registration"));
		assertEquals("W3C", matcher.group("organization"));
		assertEquals("DTD HTML 4.01", matcher.group("documentType"));
		assertEquals("EN", matcher.group("language"));
		assertEquals("http://www.w3.org/TR/html4/strict.dtd", matcher.group("url"));
	}

	/**
	 * Проверка групп.
	 */
	@Test
	public void testRegexpEndTag()
	{
		String string = "</form >";

		Pattern pattern = Pattern.compile(
			Regexp.CLOSE_TAG.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(string);

		assertTrue(matcher.find());

		assertEquals("</form >", matcher.group(Regexp.CLOSE_TAG.getGroup()));
		assertEquals("form", matcher.group("name"));
	}

	/**
	 * Проверка групп.
	 */
	@Test
	public void testRegexpSelfClosingTag()
	{
		String string = "<input name=viewport content=\"width=device-width, initial-scale=1, user-scalable=0\"/>";

		Pattern pattern = Pattern.compile(
			Regexp.SELF_CLOSING_TAG.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(string);

		assertTrue(matcher.find());

		assertEquals(
			"<input name=viewport content=\"width=device-width, initial-scale=1, user-scalable=0\"/>",
			matcher.group(Regexp.SELF_CLOSING_TAG.getGroup())
		);
		assertEquals("input", matcher.group("name"));
		assertEquals(
			" name=viewport content=\"width=device-width, initial-scale=1, user-scalable=0\"",
			matcher.group("params")
		);
	}

	/**
	 * Проверка групп.
	 */
	@Test
	public void testRegexpStartTag()
	{
		String string = "<meta name=viewport content=\"width=device-width, initial-scale=1, user-scalable=0\">";

		Pattern pattern = Pattern.compile(
			Regexp.OPEN_TAG.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(string);

		assertTrue(matcher.find());

		assertEquals(
			"<meta name=viewport content=\"width=device-width, initial-scale=1, user-scalable=0\">",
			matcher.group(Regexp.OPEN_TAG.getGroup())
		);
		assertEquals("meta", matcher.group("name"));
		assertEquals(
			" name=viewport content=\"width=device-width, initial-scale=1, user-scalable=0\"",
			matcher.group("params"));
	}

	/**
	 * Проверка групп.
	 */
	@Test
	public void testRegexpCData()
	{
		String string = "<![CDATA[ Текст ]]>";

		Pattern pattern = Pattern.compile(
			Regexp.CDATA.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(string);

		assertTrue(matcher.find());

		assertEquals("<![CDATA[ Текст ]]>", matcher.group(Regexp.CDATA.getGroup()));
		assertEquals(" Текст ", matcher.group("value"));
	}
}