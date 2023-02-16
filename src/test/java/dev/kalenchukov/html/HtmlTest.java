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

package dev.kalenchukov.html;

import dev.kalenchukov.html.resources.Entity;
import dev.kalenchukov.html.resources.EntityType;
import dev.kalenchukov.html.resources.Tag;
import dev.kalenchukov.html.resources.TagType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс проверки методов класса {@link Html}.
 */
public class HtmlTest
{
	/**
	 * Проверка метода {@link Html#getText()}.
	 */
	@Test
	public void testGetText()
	{
		String text = "text 123 текст";

		Hypertext html = new Html(text);
		html.getText();

		assertEquals(text, html.getText());
	}

	/**
	 * Проверка метода {@link Html#setText(String)}.
	 */
	@Test
	public void testSetText()
	{
		String text = "text 123 текст";

		Hypertext html = new Html("text");
		html.setText(text);

		assertEquals(text, html.getText());
	}

	/**
	 * Проверка метода {@link Html#deleteDoctype()}.
	 */
	@Test
	public void testDeleteDoctype()
	{
		String text = """
			<!DOCTYPE html>
			<b><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
				"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"></b>
			""";

		Hypertext html = new Html(text);
		html.deleteDoctype();

		assertEquals("<b></b>", html.getText().trim());
	}

	/**
	 * Проверка метода {@link Html#deleteComments()}.
	 */
	@Test
	public void testDeleteComments()
	{
		String text = """
			<html><!-- Комментарий --></html>
			<!--
				Комментарий
				с переносом
			-->
			""";

		Hypertext html = new Html(text);
		html.deleteComments();

		assertEquals("<html></html>", html.getText().trim());
	}

	/**
	 * Проверка метода {@link Html#deleteCData()}.
	 */
	@Test
	public void testDeleteCData()
	{
		String text = """
			<html>
			<![CDATA[x<y]]>Текст<![CDATA[
			cdata ->
			text
			]]>
			</html>
			""";

		Hypertext html = new Html(text);
		html.deleteCData();

		assertEquals("<html>\nТекст\n</html>", html.getText().trim());
	}

	/**
	 * Проверка метода {@link Html#deleteTag(Tag)}.
	 */
	@Test
	public void testDeleteTag()
	{
		String text = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<<i>переменный ток/постоянный ток</i>>>) — австралийская <a href = rock.html >рок-группа</a>,<br/>
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года</strong></a> выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом</a> и Ангусом Янгами.<input value = yes />
			""";

		String expected = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<<i>переменный ток/постоянный ток</i>>>) — австралийская рок-группа,<br/>
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <strong>1973
			года</strong> выходцами из Шотландии,<br>
			братьями Малькольмом и Ангусом Янгами.<input value = yes />
			""";

		Hypertext html = new Html(text);
		html.deleteTag(Tag.A);

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#deleteTags()}.
	 */
	@Test
	public void testDeleteTags()
	{
		String text = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<<i>переменный ток/постоянный ток</i>>>) — австралийская <a href = rock.html >рок-группа</a>,<br/>
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года</strong></a> выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом</a> и Ангусом Янгами.<input value = yes />
			""";

		String expected = """
			AC/DC (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			<<переменный ток/постоянный ток>>) — австралийская рок-группа,
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии,
			братьями Малькольмом и Ангусом Янгами.
			""";

		Hypertext html = new Html(text);
		html.deleteTags();

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#deleteTags(List)}.
	 */
	@Test
	public void testDeleteTagsExclude()
	{
		String text = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<<i>переменный ток/постоянный ток</i>>>) — австралийская <a href = rock.html >рок-группа</a>,<br/>
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года</strong></a> выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом</a> и Ангусом Янгами.<input value = yes />
			""";

		String expected = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<переменный ток/постоянный ток>>) — австралийская рок-группа,<br/>
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии,<br>
			братьями Малькольмом и Ангусом Янгами.
			""";

		Hypertext html = new Html(text);
		html.deleteTags(List.of(Tag.B, Tag.BR));

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#deleteTags(TagType)}.
	 */
	@Test
	public void testDeleteSelfClosingTags()
	{
		String text = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<<i>переменный ток/постоянный ток</i>>>) — австралийская <a href = rock.html >рок-группа</a>,<br/>
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года</strong></a> выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом</a> и Ангусом Янгами.<input value = yes />
			""";

		String expected = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			<<<i>переменный ток/постоянный ток</i>>>) — австралийская <a href = rock.html >рок-группа</a>,
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года</strong></a> выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом</a> и Ангусом Янгами.
			""";

		Hypertext html = new Html(text);
		html.deleteTags(TagType.SELF_CLOSING);

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#deleteTags(TagType, List)}.
	 */
	@Test
	public void testDeleteSelfClosingTagsExclude()
	{
		String text = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<<i>переменный ток/постоянный ток</i>>>) — австралийская <a href = rock.html >рок-группа</a>,<br/>
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года</strong></a> выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом</a> и Ангусом Янгами.<input value = yes />
			""";

		String expected = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<<i>переменный ток/постоянный ток</i>>>) — австралийская <a href = rock.html >рок-группа</a>,<br/>
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года</strong></a> выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом</a> и Ангусом Янгами.
			""";

		Hypertext html = new Html(text);
		html.deleteTags(TagType.SELF_CLOSING, List.of(Tag.BR));

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#deleteTag(Tag, TagType)}.
	 */
	@Test
	public void testDeleteSelfClosingTag()
	{
		String text = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<<i>переменный ток/постоянный ток</i>>>) — австралийская <a href = rock.html >рок-группа</a>,<br/>
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года</strong></a> выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом</a> и Ангусом Янгами.<input value = yes />
			""";

		String expected = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			<<<i>переменный ток/постоянный ток</i>>>) — австралийская <a href = rock.html >рок-группа</a>,
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года</strong></a> выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом</a> и Ангусом Янгами.<input value = yes />
			""";

		Hypertext html = new Html(text);
		html.deleteTag(Tag.BR, TagType.SELF_CLOSING);

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#deleteTags(TagType)}.
	 */
	@Test
	public void testDeleteOpenTags()
	{
		String text = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<<i>переменный ток/постоянный ток</i>>>) — австралийская <a href = rock.html >рок-группа</a>,<br/>
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года</strong></a> выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом</a> и Ангусом Янгами.<input value = yes />
			""";

		String expected = """
			AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<переменный ток/постоянный ток</i>>>) — австралийская рок-группа</a>,<br/>
			сформированная в Сиднее в ноябре 1973
			года</strong></a> выходцами из Шотландии,
			братьями Малькольмом</a> и Ангусом Янгами.<input value = yes />
			""";

		Hypertext html = new Html(text);
		html.deleteTags(TagType.OPEN);

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#deleteTags(TagType, List)}.
	 */
	@Test
	public void testDeleteOpenTagsExclude()
	{
		String text = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<<i>переменный ток/постоянный ток</i>>>) — австралийская <a href = rock.html >рок-группа</a>,<br/>
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года</strong></a> выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом</a> и Ангусом Янгами.<input value = yes />
			""";

		String expected = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<переменный ток/постоянный ток</i>>>) — австралийская рок-группа</a>,<br/>
			сформированная в Сиднее в ноябре 1973
			года</strong></a> выходцами из Шотландии,
			братьями Малькольмом</a> и Ангусом Янгами.<input value = yes />
			""";

		Hypertext html = new Html(text);
		html.deleteTags(TagType.OPEN, List.of(Tag.B));

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#deleteTag(Tag, TagType)}.
	 */
	@Test
	public void testDeleteOpenTag()
	{
		String text = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<<i>переменный ток/постоянный ток</i>>>) — австралийская <a href = rock.html >рок-группа</a>,<br/>
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года</strong></a> выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом</a> и Ангусом Янгами.<input value = yes />
			""";

		String expected = """
			AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<<i>переменный ток/постоянный ток</i>>>) — австралийская <a href = rock.html >рок-группа</a>,<br/>
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года</strong></a> выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом</a> и Ангусом Янгами.<input value = yes />
			""";

		Hypertext html = new Html(text);
		html.deleteTag(Tag.B, TagType.OPEN);

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#deleteTags(TagType)}.
	 */
	@Test
	public void testDeleteCloseTags()
	{
		String text = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<<i>переменный ток/постоянный ток</i>>>) — австралийская <a href = rock.html >рок-группа</a>,<br/>
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года</strong></a> выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом</a> и Ангусом Янгами.<input value = yes />
			""";

		String expected = """
			<b>AC/DC (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<<i>переменный ток/постоянный ток>>) — австралийская <a href = rock.html >рок-группа,<br/>
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом и Ангусом Янгами.<input value = yes />
			""";

		Hypertext html = new Html(text);
		html.deleteTags(TagType.CLOSE);

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#deleteTags(TagType, List)}.
	 */
	@Test
	public void testDeleteCloseTagsExclude()
	{
		String text = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<<i>переменный ток/постоянный ток</i>>>) — австралийская <a href = rock.html >рок-группа</a>,<br/>
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года</strong></a> выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом</a> и Ангусом Янгами.<input value = yes />
			""";

		String expected = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<<i>переменный ток/постоянный ток>>) — австралийская <a href = rock.html >рок-группа,<br/>
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом и Ангусом Янгами.<input value = yes />
			""";

		Hypertext html = new Html(text);
		html.deleteTags(TagType.CLOSE, List.of(Tag.B));

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#deleteTag(Tag, TagType)}.
	 */
	@Test
	public void testDeleteCloseTag()
	{
		String text = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<<i>переменный ток/постоянный ток</i>>>) — австралийская <a href = rock.html >рок-группа</a>,<br/>
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года</strong></a> выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом</a> и Ангусом Янгами.<input value = yes />
			""";

		String expected = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<<i>переменный ток/постоянный ток</i>>>) — австралийская <a href = rock.html >рок-группа,<br/>
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года</strong> выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом и Ангусом Янгами.<input value = yes />
			""";

		Hypertext html = new Html(text);
		html.deleteTag(Tag.A, TagType.CLOSE);

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#deleteEntity(Entity, EntityType)}.
	 */
	@Test
	public void testDeleteEntityName()
	{
		String text = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&#60;переменный ток&#47;постоянный ток&gt;&gt;&rpar; &#8212; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		String expected = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&#60;переменный ток&#47;постоянный ток&gt;&gt;&rpar; &#8212; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		Hypertext html = new Html(text);
		html.deleteEntity(Entity.LT, EntityType.NAME);

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#deleteEntity(Entity, EntityType)}.
	 */
	@Test
	public void testDeleteEntityNumeric()
	{
		String text = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&#60;переменный ток&#47;постоянный ток&gt;&gt;&rpar; &#8212; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		String expected = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&lt;переменный ток&#47;постоянный ток&gt;&gt;&rpar; &#8212; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		Hypertext html = new Html(text);
		html.deleteEntity(Entity.LT, EntityType.NUMERIC);

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#deleteEntity(Entity, EntityType)}.
	 */
	@Test
	public void testDeleteEntityUnicode()
	{
		String text = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&#X3C;переменный ток&#47;постоянный ток&gt;&gt;&rpar; &#8212; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		String expected = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&lt;переменный ток&#47;постоянный ток&gt;&gt;&rpar; &#8212; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		Hypertext html = new Html(text);
		html.deleteEntity(Entity.LT, EntityType.UNICODE);

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#deleteEntities()}.
	 */
	@Test
	public void testDeleteEntities()
	{
		String text = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&lt;переменный ток&#47;постоянный ток&gt;&gt;&rpar; &#8212; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		String expected = """
			ACDC эй-сиди-си сокращённо от англ alternating currentdirect current
			переменный токпостоянный ток  австралийская рок-группа
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии
			братьями Малькольмом и Ангусом Янгами
			""";

		Hypertext html = new Html(text);
		html.deleteEntities();

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#deleteEntities(List)}.
	 */
	@Test
	public void testDeleteEntitiesExclude()
	{
		String text = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&lt;переменный ток&#47;постоянный ток&gt;&gt;&rpar; &#8212; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		String expected = """
			ACDC эй-сиди-си сокращённо от англ alternating currentdirect current
			&lt;&lt;переменный токпостоянный ток&gt;&gt;  австралийская рок-группа
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии
			братьями Малькольмом и Ангусом Янгами
			""";

		Hypertext html = new Html(text);
		html.deleteEntities(List.of(Entity.LT, Entity.GT));

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#encodeEntity(Entity, EntityType)}.
	 */
	@Test
	public void testEncodeEntityName()
	{
		String text = """
			AC/DC (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			<<переменный ток/постоянный ток>>) — австралийская рок-группа,
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии,
			братьями Малькольмом и Ангусом Янгами.
			""";

		String expected = """
			AC/DC (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			&lt;&lt;переменный ток/постоянный ток>>) — австралийская рок-группа,
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии,
			братьями Малькольмом и Ангусом Янгами.
			""";

		Hypertext html = new Html(text);
		html.encodeEntity(Entity.LT, EntityType.NAME);

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#encodeEntity(Entity, EntityType)}.
	 */
	@Test
	public void testEncodeEntityNumeric()
	{
		String text = """
			AC/DC (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			<<переменный ток/постоянный ток>>) — австралийская рок-группа,
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии,
			братьями Малькольмом и Ангусом Янгами.
			""";

		String expected = """
			AC/DC (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			&#60;&#60;переменный ток/постоянный ток>>) — австралийская рок-группа,
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии,
			братьями Малькольмом и Ангусом Янгами.
			""";

		Hypertext html = new Html(text);
		html.encodeEntity(Entity.LT, EntityType.NUMERIC);

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#encodeEntity(Entity, EntityType)}.
	 */
	@Test
	public void testEncodeEntityUnicode()
	{
		String text = """
			AC/DC (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			<<переменный ток/постоянный ток>>) — австралийская рок-группа,
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии,
			братьями Малькольмом и Ангусом Янгами.
			""";

		String expected = """
			AC/DC (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			&#X3C;&#X3C;переменный ток/постоянный ток>>) — австралийская рок-группа,
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии,
			братьями Малькольмом и Ангусом Янгами.
			""";

		Hypertext html = new Html(text);
		html.encodeEntity(Entity.LT, EntityType.UNICODE);

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#encodeEntities(EntityType)}.
	 */
	@Test
	public void testEncodeEntitiesName()
	{
		String text = """
			AC/DC (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			<<переменный ток/постоянный ток>>) — австралийская рок-группа,
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии,
			братьями Малькольмом и Ангусом Янгами.
			""";

		String expected = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&lt;переменный ток&sol;постоянный ток&gt;&gt;&rpar; &mdash; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&period;
			""";

		Hypertext html = new Html(text);
		html.encodeEntities(EntityType.NAME);

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#encodeEntities(EntityType)}.
	 */
	@Test
	public void testEncodeEntitiesNumeric()
	{
		String text = """
			AC/DC (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			<<переменный ток/постоянный ток>>) — австралийская рок-группа,
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии,
			братьями Малькольмом и Ангусом Янгами.
			""";

		String expected = """
			AC&#47;DC &#40;эй-си&#47;ди-си&#59; сокращённо от англ&#46; alternating current&#47;direct current
			&#60;&#60;переменный ток&#47;постоянный ток&#62;&#62;&#41; &#8212; австралийская рок-группа&#44;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&#44;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		Hypertext html = new Html(text);
		html.encodeEntities(EntityType.NUMERIC);

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#encodeEntities(EntityType)}.
	 */
	@Test
	public void testEncodeEntitiesUnicode()
	{
		String text = """
			AC/DC (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			<<переменный ток/постоянный ток>>) — австралийская рок-группа,
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии,
			братьями Малькольмом и Ангусом Янгами.
			""";

		String expected = """
			AC&#X2F;DC &#X28;эй-си&#X2F;ди-си&#X3B; сокращённо от англ&#X2E; alternating current&#X2F;direct current
			&#X3C;&#X3C;переменный ток&#X2F;постоянный ток&#X3E;&#X3E;&#X29; &#X2014; австралийская рок-группа&#X2C;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&#X2C;
			братьями Малькольмом и Ангусом Янгами&#X2E;
			""";

		Hypertext html = new Html(text);
		html.encodeEntities(EntityType.UNICODE);

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#encodeEntities(EntityType, List)}.
	 */
	@Test
	public void testEncodeEntitiesNameExclude()
	{
		String text = """
			AC/DC (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			<<переменный ток/постоянный ток>>) — австралийская рок-группа,
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии,
			братьями Малькольмом и Ангусом Янгами.
			""";

		String expected = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			<<переменный ток&sol;постоянный ток>>&rpar; &mdash; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&period;
			""";

		Hypertext html = new Html(text);
		html.encodeEntities(EntityType.NAME, List.of(Entity.LT, Entity.GT));

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#encodeEntities(EntityType, List)}.
	 */
	@Test
	public void testEncodeEntitiesNumericExclude()
	{
		String text = """
			AC/DC (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			<<переменный ток/постоянный ток>>) — австралийская рок-группа,
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии,
			братьями Малькольмом и Ангусом Янгами.
			""";

		String expected = """
			AC&#47;DC &#40;эй-си&#47;ди-си&#59; сокращённо от англ&#46; alternating current&#47;direct current
			<<переменный ток&#47;постоянный ток>>&#41; &#8212; австралийская рок-группа&#44;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&#44;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		Hypertext html = new Html(text);
		html.encodeEntities(EntityType.NUMERIC, List.of(Entity.LT, Entity.GT));

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#encodeEntities(EntityType, List)}.
	 */
	@Test
	public void testEncodeEntitiesUnicodeExclude()
	{
		String text = """
			AC/DC (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			<<переменный ток/постоянный ток>>) — австралийская рок-группа,
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии,
			братьями Малькольмом и Ангусом Янгами.
			""";

		String expected = """
			AC&#X2F;DC &#X28;эй-си&#X2F;ди-си&#X3B; сокращённо от англ&#X2E; alternating current&#X2F;direct current
			<<переменный ток&#X2F;постоянный ток>>&#X29; &#X2014; австралийская рок-группа&#X2C;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&#X2C;
			братьями Малькольмом и Ангусом Янгами&#X2E;
			""";

		Hypertext html = new Html(text);
		html.encodeEntities(EntityType.UNICODE, List.of(Entity.LT, Entity.GT));

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#decodeEntity(Entity)}.
	 */
	@Test
	public void testDecodeEntity()
	{
		String text = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&lt;переменный ток&#47;постоянный ток&gt;&gt;&rpar; &#8212; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		String expected = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			<<переменный ток&#47;постоянный ток&gt;&gt;&rpar; &#8212; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		Hypertext html = new Html(text);
		html.decodeEntity(Entity.LT);

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#decodeEntity(Entity, EntityType)}.
	 */
	@Test
	public void testDecodeEntityName()
	{
		String text = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&lt;переменный ток&#47;постоянный ток&gt;&gt;&rpar; &#8212; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		String expected = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			<<переменный ток&#47;постоянный ток&gt;&gt;&rpar; &#8212; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		Hypertext html = new Html(text);
		html.decodeEntity(Entity.LT, EntityType.NAME);

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#decodeEntity(Entity, EntityType)}.
	 */
	@Test
	public void testDecodeEntityNumeric()
	{
		String text = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&lt;переменный ток&#47;постоянный ток&gt;&gt;&rpar; &#8212; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		String expected = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&lt;переменный ток&#47;постоянный ток&gt;&gt;&rpar; — австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		Hypertext html = new Html(text);
		html.decodeEntity(Entity.MDASH, EntityType.NUMERIC);

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#decodeEntity(Entity, EntityType)}.
	 */
	@Test
	public void testDecodeEntityUnicode()
	{
		String text = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&lt;переменный ток&#47;постоянный ток&gt;&gt;&rpar; &#X2014; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		String expected = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&lt;переменный ток&#47;постоянный ток&gt;&gt;&rpar; — австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		Hypertext html = new Html(text);
		html.decodeEntity(Entity.MDASH, EntityType.UNICODE);

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#decodeEntities()}.
	 */
	@Test
	public void testDecodeEntities()
	{
		String text = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&lt;переменный ток&#47;постоянный ток&gt;&gt;&rpar; &#8212; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		String expected = """
			AC/DC (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			<<переменный ток/постоянный ток>>) — австралийская рок-группа,
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии,
			братьями Малькольмом и Ангусом Янгами.
			""";

		Hypertext html = new Html(text);
		html.decodeEntities();

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#decodeEntities(EntityType)}.
	 */
	@Test
	public void testDecodeEntitiesName()
	{
		String text = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&#60;&#x3C;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;&rpar; &mdash; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&period;
			""";

		String expected = """
			AC/DC (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			<&#60;&#x3C;переменный ток/постоянный ток&#62;&#X0003E;>) — австралийская рок-группа,
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии,
			братьями Малькольмом и Ангусом Янгами.
			""";

		Hypertext html = new Html(text);
		html.decodeEntities(EntityType.NAME);

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#decodeEntities(EntityType)}.
	 */
	@Test
	public void testDecodeEntitiesNumeric()
	{
		String text = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&#59; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&#60;&#x3C;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;&rpar; &#x2014; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&period;
			""";

		String expected = """
			AC&sol;DC &lpar;эй-си&sol;ди-си; сокращённо от англ&period; alternating current&sol;direct current
			&lt;<&#x3C;переменный ток&sol;постоянный ток>&#X0003E;&gt;&rpar; &#x2014; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&period;
			""";

		Hypertext html = new Html(text);
		html.decodeEntities(EntityType.NUMERIC);

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#decodeEntities(EntityType)}.
	 */
	@Test
	public void testDecodeEntitiesUnicode()
	{
		String text = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&#59; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&#60;&#x3C;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;&rpar; &#x2014; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#X00002E;
			""";

		String expected = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&#59; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&#60;<переменный ток&sol;постоянный ток&#62;>&gt;&rpar; — австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами.
			""";

		Hypertext html = new Html(text);
		html.decodeEntities(EntityType.UNICODE);

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#decodeEntities(List)}.
	 */
	@Test
	public void testDecodeEntitiesExclude()
	{
		String text = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&#60;&#x3C;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;&rpar; &mdash; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&period;
			""";

		String expected = """
			AC/DC (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			&lt;&#60;&#x3C;переменный ток/постоянный ток&#62;&#X0003E;&gt;) — австралийская рок-группа,
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии,
			братьями Малькольмом и Ангусом Янгами.
			""";

		Hypertext html = new Html(text);
		html.decodeEntities(List.of(Entity.LT, Entity.GT));

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#decodeEntities(EntityType, List)}.
	 */
	@Test
	public void testDecodeEntitiesNameExclude()
	{
		String text = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&#60;&#x3C;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;&rpar; &mdash; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&period;
			""";

		String expected = """
			AC/DC (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			&lt;&#60;&#x3C;переменный ток/постоянный ток&#62;&#X0003E;&gt;) — австралийская рок-группа,
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии,
			братьями Малькольмом и Ангусом Янгами.
			""";

		Hypertext html = new Html(text);
		html.decodeEntities(EntityType.NAME, List.of(Entity.LT, Entity.GT));

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#decodeEntities(EntityType, List)}.
	 */
	@Test
	public void testDecodeEntitiesNumericExclude()
	{
		String text = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&#59; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&#60;&#x3C;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;&rpar; &#x2014; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&period;
			""";

		String expected = """
			AC&sol;DC &lpar;эй-си&sol;ди-си; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&#60;&#x3C;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;&rpar; &#x2014; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&period;
			""";

		Hypertext html = new Html(text);
		html.decodeEntities(EntityType.NUMERIC, List.of(Entity.LT, Entity.GT));

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#decodeEntities(EntityType, List)}.
	 */
	@Test
	public void testDecodeEntitiesUnicodeExclude()
	{
		String text = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&#59; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&#60;&#x3C;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;&rpar; &#x2014; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#X00002E;
			""";

		String expected = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&#59; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&#60;&#x3C;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;&rpar; — австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами.
			""";

		Hypertext html = new Html(text);
		html.decodeEntities(EntityType.UNICODE, List.of(Entity.LT, Entity.GT));

		assertEquals(expected, html.getText());
	}

	/**
	 * Проверка метода {@link Html#isComment()}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"<!---->", "<!-- -->", "<!--  -->",
		"<!-- 12345 -->", "<!-- Комментарий -->", "<!-- Comment -->",
		"<!-- Comment-->", "<!--Комментарий -->",
		"<!--\n\t12345\n-->", "<!--\n12345 -->"
	})
	public void testIsComment(String value)
	{
		assertTrue(new Html(value).isComment());
	}

	/**
	 * Проверка метода {@link Html#isComment()} с некорректным значением.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"", " ",
		"34546<!-- Comment -->",
		"<!- Комментарий -->", "<-- Комментарий -->",
		"<!-- Комментарий ->", "<!-- Комментарий --",
		"<!--< Comment -->", "<!---> Comment -->", "<!-- Comment<!--->",
		"<!-- Comm<!--ent -->", "<!-- Comm-->ent -->", "<!-- Comm--!>ent -->",
		"<!-- <!--Comment -->", "<!-- -->Comment -->", "<!-- --!>Comment -->",
		"<!-- Comment<!-- -->", "<!-- Comment--> -->", "<!-- Comment--!> -->",
		"<!--\n\tComment\n-->\n"
	})
	public void testIsCommentNotCorrect(String value)
	{
		assertFalse(new Html(value).isComment());
	}

	/**
	 * Проверка метода {@link Html#isEntity()}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"&dd;", "&dollar;", "&DownArrowBar;", "&ecir;", "&DD;", "&frac14;",
		"&#038;", "&#38;", "&#256;", "&#0038;", "&#8501;", "&#10590;", "&#010590;", "&#0010590;", "&#0000000000000010590;",
		"&#XB0;", "&#x394;", "&#X2223;", "&#X154;", "&#x00000000000000BB;"
	})
	public void testIsEntity(String value)
	{
		assertTrue(new Html(value).isEntity());
	}

	/**
	 * Проверка метода {@link Html#isEntity()}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"", " ",
		"34546&DownArrowBar;", "&", ";", "&;", "&d;", "ecir;", "&ecir", "&3124;", "&1DownArrowBar;",
		"34546&#8501;", "&#3;", "#256;", "&256;", "&#256", "&#2D56;", "34546&#XB0;",
		"&#x3;", "#XB0;", "&#B0;", "&#B0"
	})
	public void testIsEntityNotCorrect(String value)
	{
		assertFalse(new Html(value).isEntity());
	}

	/**
	 * Проверка метода {@link Html#isEntityName()}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"&dd;", "&dollar;", "&DownArrowBar;", "&ecir;", "&DD;", "&frac14;"
	})
	public void testIsEntityName(String value)
	{
		assertTrue(new Html(value).isEntityName());
	}

	/**
	 * Проверка метода {@link Html#isEntityName} с некорректным значением.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"", " ",
		"34546&DownArrowBar;",
		"&", ";", "&;", "&d;", "ecir;", "&ecir", "&3124;", "&1DownArrowBar;"
	})
	public void testIsEntityNameNotCorrect(String value)
	{
		assertFalse(new Html(value).isEntityName());
	}

	/**
	 * Проверка метода {@link Html#isEntityNumeric()}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"&#038;", "&#38;", "&#256;", "&#0038;", "&#8501;",
		"&#10590;", "&#010590;", "&#0010590;", "&#0000000000000010590;"
	})
	public void testIsEntityNumeric(String value)
	{
		assertTrue(new Html(value).isEntityNumeric());
	}

	/**
	 * Проверка метода {@link Html#isEntityNumeric} с некорректным значением.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"", " ", "34546&#8501;", "&#3;",
		"#256;", "&256;", "&#256", "&#2D56;"
	})
	public void testIsEntityNumericNotCorrect(String value)
	{
		assertFalse(new Html(value).isEntityNumeric());
	}

	/**
	 * Проверка метода {@link Html#isEntityUnicode()}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"&#XB0;", "&#x394;", "&#X2223;", "&#X154;", "&#x00000000000000BB;"
	})
	public void testIsEntityUnicode(String value)
	{
		assertTrue(new Html(value).isEntityUnicode());
	}

	/**
	 * Проверка метода {@link Html#isEntityUnicode()} с некорректным значением.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"", " ",
		"34546&#XB0;", "&#x3;",
		"#XB0;", "&XB0;", "&#B0;", "&#B0"
	})
	public void testIsEntityUnicodeNotCorrect(String value)
	{
		assertFalse(new Html(value).isEntityUnicode());
	}

	/**
	 * Проверка метода {@link Html#isDoctype()}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"<!DOCTYPE html>",
		"<!DOCTYPE  html >",
		"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">",
		"<!DOCTYPE HTML PUBLIC \"+//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">",
		"<!DOCTYPE HTML PUBLIC   '-//W3C//DTD HTML 4.01 Transitional//EN' \"http://www.w3.org/TR/html4/loose.dtd\">",
		"<!DOCTYPE HTML  PUBLIC \"-//W3C//DTD HTML 4.01 Frameset//EN\"  'http://www.w3.org/TR/html4/frameset.dtd'>",
		"<!DOCTYPE html  PUBLIC  '-//W3C//DTD XHTML 1.0 Strict//EN'   'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd' >",
		"<!DOCTYPE html PUBLIC\n'-//W3C//DTD XHTML 1.0 Frameset//EN'\n'http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd' \n>",
		"<!DOCTYPE html PUBLIC\n\t\"-//W3C//DTD XHTML 1.1//EN\"\n\"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">"
	})
	public void testIsDoctype(String value)
	{
		assertTrue(new Html(value).isDoctype());
	}

	/**
	 * Проверка метода {@link Html#isDoctype()} с некорректным значением.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"", " ",
		"<DOCTYPE>",
		"<DOCTYPE html>",
		"text<!DOCTYPE html>",
		"<!DOCTYPE HTML PUBLIC>",
		"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"\">",
		"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\">",
		"<!DOCTYPE HTML PUBLIC \"//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">",
		"<!DOCTYPE HTML PUBLIC \"-//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">",
		"<!DOCTYPE HTML PUBLIC \"-//W3C//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">",
		"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01\" \"http://www.w3.org/TR/html4/strict.dtd\">",
		"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//\" \"http://www.w3.org/TR/html4/strict.dtd\">",
		"<!DOCTYPE HTML PUBLIC \"\" \"http://www.w3.org/TR/html4/strict.dtd\">",
		"<!DOCTYPE HTML PUBLIC \"http://www.w3.org/TR/html4/strict.dtd\">",
		"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR'/html4/strict.dtd\">",
		"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" 'http://www.w3.org/TR\"/html4/strict.dtd'>"
	})
	public void testIsDoctypeNotCorrect(String value)
	{
		assertFalse(new Html(value).isDoctype());
	}

	/**
	 * Проверка метода {@link Html#isTag()}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"<form>", "<b >", "<br  >", "<input value>", "<input value=yes>",
		"</form>", "</form >", "</form  >",
		"<meta/>", "<meta />",
		"<meta charset='UTF-8' />", "<meta charset=\"UTF-8\"/>"

	})
	public void testIsTag(String value)
	{
		assertTrue(new Html(value).isTag());
	}

	/**
	 * Проверка метода {@link Html#isTag()} с некорректным значением.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"", " ",
		"text<input>", "<link href=>", "<input name='text\">",
		"</ form>", "< /form>", "text</form>",
		"text<input/>", "<input name='text\"/>"
	})
	public void testIsTagNotCorrect(String value)
	{
		assertFalse(new Html(value).isTag());
	}

	/**
	 * Проверка метода {@link Html#isCloseTag()}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"</form>", "</form >", "</form  >"
	})
	public void testIsCloseTag(String value)
	{
		assertTrue(new Html(value).isCloseTag());
	}

	/**
	 * Проверка метода {@link Html#isCloseTag()}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"", " ", "</ form>", "< /form>", "text</form>"
	})
	public void testIsCloseTagNotCorrect(String value)
	{
		assertFalse(new Html(value).isCloseTag());
	}

	/**
	 * Проверка метода {@link Html#isSelfClosingTag()}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"<meta/>", "<meta />",
		"<meta charset='UTF-8' />", "<meta charset=\"UTF-8\"/>",
		"<meta />", "<meta  />",
		"<meta value/>", "<meta value=yes/>", "<meta value = yes />", "<meta value  =  yes  />",
		"<input id=''/>", "<input id=\"\"/>",
		"<meta charset='UTF-8'/>", "<meta charset=\"UTF-8\"/>",
		"<meta name='123'/>", "<meta name=' текст'/>", "<meta name=' текст 123'/>",
		"<meta name=\"input name\"/>", "<meta name=\"0123456789\"/>",
		"<input id='input-id'/>", "<input id=\"input-id-123\"/>",
		"<input name=\"viewport\" content=\"width=device-width, initial-scale=1, user-scalable=0\"/>",
		"<input name=viewport content=\"width=device-width, initial-scale=1, user-scalable=0\"/>",
		"<input id  ='ID' qwe-attr =  \"my_attribute\"  />", "<input qwe_attr =  \"my_attribute\"  />",
		"<link\n\trel=\"icon\"\ntype='image/png'\n\tsizes=\"\"\n\thref=''/>"
	})
	public void testIsSelfClosingTag(String value)
	{
		assertTrue(new Html(value).isSelfClosingTag());
	}

	/**
	 * Проверка метода {@link Html#isSelfClosingTag()} с некорректным значением.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"", " ",
		"text<input/>", "<link href=/>", "<input name='text\"/>",
		"<input name=text'/>", "<input name='text/>", "<input name='te'xt'/>",
		"<input name=text\"/>", "<input name=\"text/>", "<input name=\"te\"xt\"/>",
		"< input/>", "<input/", "input/>",
		"<input id=='input-id'/>", "<input id = = 'input-id'/>",
		"<input id=''input-id'/>", "<input id='input-id''/>",
		"<link\n\trel=\"icon\"\ntype='image/png'\n\tsizes=\"\"\n\thref=/>"
	})
	public void testIsSelfClosingTagNotCorrect(String value)
	{
		assertFalse(new Html(value).isSelfClosingTag());
	}

	/**
	 * Проверка метода {@link Html#isOpenTag()}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"<form>", "<form >", "<form  >",
		"<input value>", "<input value=yes>", "<input value = yes >", "<input value  =  yes  >",
		"<input id=''>", "<input id=\"\">",
		"<input type=\"checkbox\">", "<input type='checkbox'>",
		"<input name='123'>", "<input name=' текст'>", "<input name=' текст 123'>",
		"<input name=\"input name\">", "<input name=\"0123456789\">",
		"<input id='input-id'>", "<input id=\"input-id-123\">",
		"<meta charset=\"UTF-8\">",
		"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, user-scalable=0\">",
		"<meta name=viewport content=\"width=device-width, initial-scale=1, user-scalable=0\">",
		"<input id  ='ID' qwe-attr =  \"my_attribute\"  >",
		"<input qwe_attr =  \"my_attribute\"  >",
		"<link\n\trel=\"icon\"\ntype='image/png'\n\tsizes=\"\"\nhref=''>"
	})
	public void testIsOpenTag(String value)
	{
		assertTrue(new Html(value).isOpenTag());
	}

	/**
	 * Проверка метода {@link Html#isOpenTag()} с некорректным значением.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"", " ",
		"text<input>", "<link href=>", "<input name='text\">",
		"<input name=text'>", "<input name='text>", "<input name='te'xt'>",
		"<input name=text\">", "<input name=\"text>", "<input name=\"te\"xt\">",
		"< input>", "<input", "input>",
		"<input id=='input-id'>", "<input id = = 'input-id'>",
		"<input id=''input-id'>", "<input id='input-id''>",
		"<link\n\trel=\"icon\"\ntype='image/png'\n\tsizes=\"\"\n\thref=>"
	})
	public void testIsOpenTagNotCorrect(String value)
	{
		assertFalse(new Html(value).isOpenTag());
	}

	/**
	 * Проверка метода {@link Html#isCData()}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"<![CDATA[]]>", "<![CDATA[ ]]>", "<![CDATA[  ]]>",
		"<![CDATA[ 12345 ]]>", "<![CDATA[ Текст ]]>", "<![CDATA[ Text ]]>",
		"<![CDATA[ Text]]>", "<![CDATA[Текст ]]>",
		"<![CDATA[\n\n]]>", "<![CDATA[\n\t12345\n]]>", "<![CDATA[\n\t12345 ]]>"
	})
	public void testIsCData(String value)
	{
		assertTrue(new Html(value).isCData());
	}

	/**
	 * Проверка метода {@link Html#isCData()} с некорректным значением.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"", " ",
		"2134<![CDATA[ Text ]]>", "<[CDATA[ Текст ]]>",
		"<![CDATA[ Текст ]>", "<![CDATA[ Текст ]]",
		"<![CDATA[ Te]]>xt ]]>", "<![CDATA[ ]]>Text ]]>", "<![CDATA[ Text]]> ]]>",
		"<![CDATA[\n\tText\n]]>\n"
	})
	public void testIsCDataNotCorrect(String value)
	{
		assertFalse(new Html(value).isCData());
	}

	/**
	 * Проверка метода {@link Html#findComment()}.
	 */
	@Test
	public void testFindComment()
	{
		String[] htmlComment = {
			"<!--Всё в свое время, зима и весна-->",
			"<!-- яблоку место упасть\nКаждому вору возможность украсть\nКаждой собаке палку и кость-->",
			"<!--,-->",
			"<!---->"
		};

		String text = """
			Песня без слов, ночь без сна
			<!--Всё в свое время, зима и весна-->
			Каждой звезде свой неба кусок
			Каждому морю дождя глоток
			Каждому <!-- яблоку место упасть
			Каждому вору возможность украсть
			Каждой собаке палку и кость-->
			И каждому волку зубы и злость
			   
			Снова за окнами белый день
			День вызывает меня на бой
			Я чувствую, закрывая глаза
			Весь мир идёт на меня войной
			   
			Если есть стадо, есть пастух
			Если есть тело, должен быть дух
			Если есть шаг<!--,--> должен быть след
			Если есть тьма, должен быть свет
			Хочешь ли ты изменить этот мир?
			Сможешь ли ты принять как есть?
			Встать и выйти из ряда вон?
			Сесть на электрический стул или трон?
			<!---->
			Снова за окнами белый день
			День вызывает меня на бой
			Я чувствую, закрывая глаза
			Весь мир идёт на меня войной
			""";

		Hypertext html = new Html(text);

		assertArrayEquals(htmlComment, html.findComment().toArray());
	}

	/**
	 * Проверка метода {@link Html#findCData()}.
	 */
	@Test
	public void testFindCData()
	{
		String[] cData = {
			"<![CDATA[Здесь не понятно, где лицо, а где рыло,]]>",
			"<![CDATA[Здесь в сено не втыкаются вилы,\nА рыба проходит сквозь сеть.\nИ не ясно, где море, где суша,\nГде золото, а где медь.]]>",
			"<![CDATA[...]]>",
			"<![CDATA[]]>"
		};

		String text = """
			<![CDATA[Здесь не понятно, где лицо, а где рыло,]]>
			И не понятно, где пряник, где плеть.
			<![CDATA[Здесь в сено не втыкаются вилы,
			А рыба проходит сквозь сеть.
			И не ясно, где море, где суша,
			Где золото, а где медь.]]>
			Что построить, и что разрушить,
			И кому, и зачем здесь петь?
			
			Нам с тобой: голубых небес навес.
			Нам с тобой: станет лес глухой стеной.
			Нам с тобой: из заплеванных колодцев не пить.
			План такой – нам с тобой<![CDATA[...]]>
			
			Здесь камни похожи на мыло,
			А сталь похожа на жесть,
			И слабость, как сила,
			И правда, как лесть.
			И не ясно, где мешок, а где шило,
			И не ясно, где обида, а где месть.
			И мне не нравится то, что здесь было,
			И мне не нравится то, что здесь есть.
			<![CDATA[]]>
			Нам с тобой: голубых небес навес.
			Нам с тобой: станет лес глухой стеной.
			Нам с тобой: из заплеванных колодцев не пить.
			План такой – нам с тобой...
			
			Чёрная ночь да в реке вода - нам с тобой.
			И беда станет не беда. Уезжай...
			Так, была не была, прости и прощай.
			План такой - нам с тобой...
			""";

		Hypertext html = new Html(text);

		assertArrayEquals(cData, html.findCData().toArray());
	}

	/**
	 * Проверка метода {@link Html#findEntity()}.
	 */
	@Test
	public void testFindEntity()
	{
		String[] htmlEntity = {
			"&frac14;",
			"&amp;",
			"&commat;",
			"&lt;",

			"&#44;",
			"&#169;",
			"&#10589;",
			"&#10590;",

			"&#X22C8;",
			"&#x224E;",
			"&#X0000141;",
			"&#X000000BB;"
		};

		String text = """
			Как много веселых ребят,
			И все делают &frac14; велосипед,
			А один из них как-нибудь утром придумает порох.
			Ну а я здесь сижу без тебя,&amp;
			Мне до этих ребят &#44; дела нет,
			Лишь окурки лежат на полу, да мусора ворох.
			
			Расскажи мне историю этого мира,
			Удивись &#169;количеству прожитых лет,
			Расскажи, &commat;каково быть мишенью в тире,
			У меня есть вопрос, на который ты не дашь мне ответ.
			
			Так странно проходят часы,&#10589;
			И так странно не хочется спать,
			&#10590;И так странно, когда за окном проезжает машина.
			И я не знаю, точны ли &#X22C8;весы,
			Но мне &#x224E; не хочется их проверять,
			Мне слишком нравится эта картина.
			
			Расскажи мне историю этого мира,
			Удивись количеству прожитых лет&lt;,&#X0000141;
			Расскажи, каково быть мишенью в тире,
			У меня есть вопрос, на который ты не дашь мне ответ.
			&#X000000BB;""";

		Hypertext html = new Html(text);

		assertArrayEquals(htmlEntity, html.findEntity().toArray());
	}

	/**
	 * Проверка метода {@link Html#findEntityName()}.
	 */
	@Test
	public void testFindEntityName()
	{
		String[] htmlEntityName = {
			"&frac14;",
			"&amp;",
			"&commat;",
			"&lt;"
		};

		String text = """
			Как много веселых ребят,
			И все делают &frac14; велосипед,
			А один из них как-нибудь утром придумает порох.
			Ну а я здесь сижу без тебя,&amp;
			Мне до этих ребят дела нет,
			Лишь окурки лежат на полу, да мусора ворох.
			
			Расскажи мне историю этого мира,
			Удивись количеству прожитых лет,
			Расскажи, &commat;каково быть мишенью в тире,
			У меня есть вопрос, на который ты не дашь мне ответ.
			
			Так странно проходят часы,
			И так странно не хочется спать,
			И так странно, когда за окном проезжает машина.
			И я не знаю, точны ли весы,
			Но мне не хочется их проверять,
			Мне слишком нравится эта картина.
			
			Расскажи мне историю этого мира,
			Удивись количеству прожитых лет&lt;,
			Расскажи, каково быть мишенью в тире,
			У меня есть вопрос, на который ты не дашь мне ответ.
			""";

		Hypertext html = new Html(text);

		assertArrayEquals(htmlEntityName, html.findEntityName().toArray());
	}

	/**
	 * Проверка метода {@link Html#findEntityNumeric()}.
	 */
	@Test
	public void testFindEntityNumeric()
	{
		String[] htmlEntityNumeric = {
			"&#44;",
			"&#169;",
			"&#10589;",
			"&#10590;"
		};

		String text = """
			В моем доме не видно стены,
			В моем небе &#44;не видно луны.
			Я слеп, но я вижу тебя,
			Я глух, но я слышу тебя.
			Я не сплю, &#169; но я вижу сны,
			Здесь нет моей вины,
			Я нем, но ты слышишь меня,
			И этим мы сильны.
			
			И снова приходит ночь,
			Я пьян, но я слышу дождь,
			Дождь для нас...
			Квартира пуста, но мы здесь,
			Здесь мало, что есть, но мы есть.
			Дождь для нас...
			
			Ты видишь мою звезду,
			Ты веришь, что я пойду.
			Я слеп, я не вижу звезд,
			Я пьян, но я помню свой пост.
			Ты смотришь на Млечный Путь,
			Я - ночь, а ты -&#10589; утра суть.
			Я - сон, я не видим тебе,
			Я слеп, но я вижу свет.
			
			И снова приходит ночь,
			Я пьян, но я слышу дождь,
			Дождь для нас...&#10590;
			Квартира пуста, но мы здесь,
			Здесь мало, что есть, но мы есть.
			Дождь для нас...
			""";

		Hypertext html = new Html(text);

		assertArrayEquals(htmlEntityNumeric, html.findEntityNumeric().toArray());
	}

	/**
	 * Проверка метода {@link Html#findEntityUnicode()}.
	 */
	@Test
	public void testFindEntityUnicode()
	{
		String[] htmlEntityUnicode = {
			"&#X22C8;",
			"&#x224E;",
			"&#X0000141;",
			"&#X000000BB;"
		};

		String text = """
			Ночь, день -
			Спать &#X22C8;лень.
			Есть дым -&#x224E;
			Чёрт с ним.
			Сна нет -
			Есть сон лет.
			Кино -
			Кончилось давно.
			
			Мой дом,
			Я в нём
			Сижу -
			Пень пнём.
			Есть свет&#X0000141;,
			Сна нет.
			Есть ночь -
			Уже уходит прочь.
			
			Стоит таз,
			Горит газ.
			Щелчок -
			&#X000000BB;И газ погас.
			Пора спать -
			В кровать.
			Вставать,
			Завтра вставать.
			""";

		Hypertext html = new Html(text);

		assertArrayEquals(htmlEntityUnicode, html.findEntityUnicode().toArray());
	}

	/**
	 * Проверка метода {@link Html#findDoctype()}.
	 */
	@Test
	public void testFindDoctype()
	{
		String[] htmlDoctype = {
			"<!DOCTYPE html>",
			"<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Strict//EN'\n'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd'>",
			"<!DOCTYPE html PUBLIC \"+//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">",
			"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" 'http://www.w3.org/TR/html4/loose.dtd'>"
		};

		String text = """
			<!DOCTYPE html>Они говорят: им нельзя рисковать,
			Потому что у них есть дом,
			В доме горит свет.
			И я не знаю точно, <!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Strict//EN'
			'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd'> кто из нас прав,
			Меня ждет на улице дождь,
			Их ждет дома обед.
			<!DOCTYPE html PUBLIC "+//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
			Закрой за мной дверь.
			Я ухожу.
			
			И если тебе вдруг наскучит твой ласковый свет,
			Тебе найдется место у нас,
			Дождя хватит на всех.
			Посмотри на часы, посмотри на портрет на стене,
			Прислушайся –<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 'http://www.w3.org/TR/html4/loose.dtd'> там, за окном,
			Ты услышишь наш смех.
			
			Закрой за мной дверь.
			Я ухожу.
			""";

		Hypertext html = new Html(text);

		assertArrayEquals(htmlDoctype, html.findDoctype().toArray());
	}

	/**
	 * Проверка метода {@link Html#findTags()}.
	 */
	@Test
	public void testFindTags()
	{
		String[] htmlTags = {
			"<b>",
			"<meta charset='UTF-8'>",
			"<input type='checkbox'  >",
			"<input value =  yes>",

			"</b>",
			"</b >",
			"</table>",
			"</strong>",

			"<br/>",
			"<meta charset='UTF-8'/>",
			"<input type='checkbox'  />",
			"<input value =  yes/>"
		};

		String text = """
			О-o, это <b>странное место Камчатка,
			О-o, это сладкое слово "Камчатка".<meta charset='UTF-8'>
			Но на этой земле я не вижу тебя,
			Я не вижу твоих кораблей,
			Я не вижу реки, я не вижу моста,
			Ну и пусть...
			<input type='checkbox'  >
			О-o, это странное место Камчатка,<input value =  yes>
			О-o, это сладкое слово "Камчатка".</b>
			Я нашел здесь руду, </b >я нашел здесь любовь,
			Я пытаюсь забыть, забываю и вновь
			Вспоминаю собаку</table>, она, как звезда,
			Ну и пусть...</strong>
			
			О-o, это странное место Камчатка,<br/>
			О-o, это сладкое слово "Камчатка".
			Я не вижу здесь их, <meta charset='UTF-8'/>я не вижу здесь нас,
			Я искал здесь вино, а нашел третий глаз,
			Мои руки из дуба, голова из свинца,<input type='checkbox'  />
			Ну и пусть...<input value =  yes/>
			""";

		Hypertext html = new Html(text);

		assertArrayEquals(htmlTags, html.findTags().toArray());
	}

	/**
	 * Проверка метода {@link Html#findCloseTag()}.
	 */
	@Test
	public void testFindCloseTag()
	{
		String[] htmlCloseTag = {
			"</form>",
			"</b >",
			"</table>",
			"</strong>",
		};

		String text = """
			О-o, это странное место Камчатка,
			О-o, это сладкое</form> слово "Камчатка".
			Но на этой земле я не вижу тебя,
			Я не вижу твоих кораблей,
			Я не вижу реки, я не вижу моста,
			Ну и пусть...</b >
			
			О-o, это странное место Камчатка,
			О-o, это сладкое слово "Камчатка".
			Я нашел здесь руду</table>, я нашел здесь любовь,
			Я пытаюсь забыть, забываю и вновь
			Вспоминаю собаку, она, как звезда,
			Ну и пусть...
			
			О-o, это странное место Камчатка,
			О-o, это сладкое слово "Камчатка"</strong>.
			Я не вижу здесь их, я не вижу здесь нас,
			Я искал здесь вино, а нашел третий глаз,
			Мои руки из дуба, голова из свинца,
			Ну и пусть...
			""";

		Hypertext html = new Html(text);

		assertArrayEquals(htmlCloseTag, html.findCloseTag().toArray());
	}

	/**
	 * Проверка метода {@link Html#findOpenTag()}.
	 */
	@Test
	public void testFindOpenTag()
	{
		String[] htmlOpenTag = {
			"<b>",
			"<meta charset='UTF-8'>",
			"<input type='checkbox'  >",
			"<input value =  yes>",
		};

		String text = """
			Солдат шел по улице домой
			И <b>увидел этих ребят.
			"Кто ваша мама, ребята?" -
			<meta charset='UTF-8'>Спросил у ребят солдат.
			
			Мама - Анархия,
			Папа - стакан портвейна.
			
			Все они в кожаных куртках,
			Все небольшого роста,
			Хотел солдат пройти мимо<input type='checkbox'  >,
			Но это было не просто.
			
			Мама - Анархия,
			Папа - стакан портвейна.
			
			Довольно веселую шутку
			Сыграли с солдатом ребята:<input value =  yes>
			Раскрасили красным и синим,
			Заставляли ругаться матом.
			
			Мама - Анархия,
			Папа - стакан портвейна.
			""";

		Hypertext html = new Html(text);

		assertArrayEquals(htmlOpenTag, html.findOpenTag().toArray());
	}

	/**
	 * Проверка метода {@link Html#findSelfClosingTag()}.
	 */
	@Test
	public void testFindSelfClosingTag()
	{
		String[] htmlOpenTag = {
			"<br/>",
			"<meta charset='UTF-8'/>",
			"<input type='checkbox'  />",
			"<input value =  yes/>",
		};

		String text = """
			У меня есть дом, только нет ключей,
			У меня есть солнце, но оно среди туч,<br/>
			Есть голова<meta charset='UTF-8'/>, только нет плечей,
			Но я вижу, как тучи режут солнечный луч.
			У меня есть слово, но в нем нет букв,
			У меня есть лес, но нет топоров,
			У меня есть время, но нет сил ждать,
			И есть еще ночь, но в ней нет снов.
			
			И есть еще белые, белые дни,
			Белые горы и белый лед.
			Но все, что мне нужно <input type='checkbox'  />- это несколько слов
			И место для шага вперед.
			
			У меня река, только нет моста,
			У меня есть мыши, но нет кота,
			У меня есть парус, но ветра нет
			И есть еще краски, но нет холста.
			У меня на <input value =  yes/> кухне из крана вода,
			У меня есть рана, но нет бинта,
			У меня есть братья, но нет родных
			И есть рука, и она пуста.
			
			И есть еще белые, белые дни,
			Белые горы и белый лед.
			Но все, что мне нужно - это несколько слов
			И место для шага вперед.
			""";

		Hypertext html = new Html(text);

		assertArrayEquals(htmlOpenTag, html.findSelfClosingTag().toArray());
	}
}