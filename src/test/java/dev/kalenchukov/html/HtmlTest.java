/*
 * Copyright © 2022 Алексей Каленчуков
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
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class HtmlTest
{
	@Test
	public void testGetText()
	{
		String text = "text 123 текст";

		Hipertext html = new Html(text);
		html.getText();

		assertEquals(text, html.getText());
	}

	@Test
	public void testSetText()
	{
		String text = "text 123 текст";

		Hipertext html = new Html("text");
		html.setText(text);

		assertEquals(text, html.getText());
	}

	@Test
	public void testDeleteDoctype()
	{
		String text = """
			<!DOCTYPE html>
			<b><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
				"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"></b>
			""";

		Hipertext html = new Html(text);
		html.deleteDoctype();

		assertEquals("<b></b>", html.getText().trim());
	}

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

		Hipertext html = new Html(text);
		html.deleteComments();

		assertEquals("<html></html>", html.getText().trim());
	}

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

		Hipertext html = new Html(text);
		html.deleteCData();

		assertEquals("<html>\nТекст\n</html>", html.getText().trim());
	}

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

		Hipertext html = new Html(text);
		html.deleteTag(Tag.A);

		assertEquals(expected, html.getText());
	}

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

		Hipertext html = new Html(text);
		html.deleteTags();

		assertEquals(expected, html.getText());
	}

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

		Hipertext html = new Html(text);
		html.deleteTags(List.of(Tag.B, Tag.BR));

		assertEquals(expected, html.getText());
	}

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

		Hipertext html = new Html(text);
		html.deleteTags(TagType.SELF_CLOSING);

		assertEquals(expected, html.getText());
	}

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

		Hipertext html = new Html(text);
		html.deleteTags(TagType.SELF_CLOSING, List.of(Tag.BR));

		assertEquals(expected, html.getText());
	}

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

		Hipertext html = new Html(text);
		html.deleteTag(Tag.BR, TagType.SELF_CLOSING);

		assertEquals(expected, html.getText());
	}

	@Test
	public void testDeleteStartTags()
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

		Hipertext html = new Html(text);
		html.deleteTags(TagType.START);

		assertEquals(expected, html.getText());
	}

	@Test
	public void testDeleteStartTagsExclude()
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

		Hipertext html = new Html(text);
		html.deleteTags(TagType.START, List.of(Tag.B));

		assertEquals(expected, html.getText());
	}

	@Test
	public void testDeleteStartTag()
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

		Hipertext html = new Html(text);
		html.deleteTag(Tag.B, TagType.START);

		assertEquals(expected, html.getText());
	}

	@Test
	public void testDeleteEndTags()
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

		Hipertext html = new Html(text);
		html.deleteTags(TagType.END);

		assertEquals(expected, html.getText());
	}

	@Test
	public void testDeleteEndTagsExclude()
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

		Hipertext html = new Html(text);
		html.deleteTags(TagType.END, List.of(Tag.B));

		assertEquals(expected, html.getText());
	}

	@Test
	public void testDeleteEndTag()
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

		Hipertext html = new Html(text);
		html.deleteTag(Tag.A, TagType.END);

		assertEquals(expected, html.getText());
	}

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

		Hipertext html = new Html(text);
		html.deleteEntity(Entity.LT, EntityType.NAME);

		assertEquals(expected, html.getText());
	}

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

		Hipertext html = new Html(text);
		html.deleteEntity(Entity.LT, EntityType.NUMERIC);

		assertEquals(expected, html.getText());
	}

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

		Hipertext html = new Html(text);
		html.deleteEntity(Entity.LT, EntityType.UNICODE);

		assertEquals(expected, html.getText());
	}

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

		Hipertext html = new Html(text);
		html.deleteEntities();

		assertEquals(expected, html.getText());
	}

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

		Hipertext html = new Html(text);
		html.deleteEntities(List.of(Entity.LT, Entity.GT));

		assertEquals(expected, html.getText());
	}

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

		Hipertext html = new Html(text);
		html.encodeEntity(Entity.LT, EntityType.NAME);

		assertEquals(expected, html.getText());
	}

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

		Hipertext html = new Html(text);
		html.encodeEntity(Entity.LT, EntityType.NUMERIC);

		assertEquals(expected, html.getText());
	}

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

		Hipertext html = new Html(text);
		html.encodeEntity(Entity.LT, EntityType.UNICODE);

		assertEquals(expected, html.getText());
	}

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

		Hipertext html = new Html(text);
		html.encodeEntities(EntityType.NAME);

		assertEquals(expected, html.getText());
	}

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

		Hipertext html = new Html(text);
		html.encodeEntities(EntityType.NUMERIC);

		assertEquals(expected, html.getText());
	}

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

		Hipertext html = new Html(text);
		html.encodeEntities(EntityType.UNICODE);

		assertEquals(expected, html.getText());
	}

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

		Hipertext html = new Html(text);
		html.encodeEntities(EntityType.NAME, List.of(Entity.LT, Entity.GT));

		assertEquals(expected, html.getText());
	}

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

		Hipertext html = new Html(text);
		html.encodeEntities(EntityType.NUMERIC, List.of(Entity.LT, Entity.GT));

		assertEquals(expected, html.getText());
	}

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

		Hipertext html = new Html(text);
		html.encodeEntities(EntityType.UNICODE, List.of(Entity.LT, Entity.GT));

		assertEquals(expected, html.getText());
	}

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

		Hipertext html = new Html(text);
		html.decodeEntity(Entity.LT);

		assertEquals(expected, html.getText());
	}

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

		Hipertext html = new Html(text);
		html.decodeEntity(Entity.LT, EntityType.NAME);

		assertEquals(expected, html.getText());
	}

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

		Hipertext html = new Html(text);
		html.decodeEntity(Entity.MDASH, EntityType.NUMERIC);

		assertEquals(expected, html.getText());
	}

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

		Hipertext html = new Html(text);
		html.decodeEntity(Entity.MDASH, EntityType.UNICODE);

		assertEquals(expected, html.getText());
	}

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

		Hipertext html = new Html(text);
		html.decodeEntities();

		assertEquals(expected, html.getText());
	}

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

		Hipertext html = new Html(text);
		html.decodeEntities(EntityType.NAME);

		assertEquals(expected, html.getText());
	}

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

		Hipertext html = new Html(text);
		html.decodeEntities(EntityType.NUMERIC);

		assertEquals(expected, html.getText());
	}

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

		Hipertext html = new Html(text);
		html.decodeEntities(EntityType.UNICODE);

		assertEquals(expected, html.getText());
	}

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

		Hipertext html = new Html(text);
		html.decodeEntities(List.of(Entity.LT, Entity.GT));

		assertEquals(expected, html.getText());
	}

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

		Hipertext html = new Html(text);
		html.decodeEntities(EntityType.NAME, List.of(Entity.LT, Entity.GT));

		assertEquals(expected, html.getText());
	}

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

		Hipertext html = new Html(text);
		html.decodeEntities(EntityType.NUMERIC, List.of(Entity.LT, Entity.GT));

		assertEquals(expected, html.getText());
	}

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

		Hipertext html = new Html(text);
		html.decodeEntities(EntityType.UNICODE, List.of(Entity.LT, Entity.GT));

		assertEquals(expected, html.getText());
	}
}