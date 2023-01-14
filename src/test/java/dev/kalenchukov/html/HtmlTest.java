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
		html.deleteTags(TagType.OPEN);

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
		html.deleteTags(TagType.OPEN, List.of(Tag.B));

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
		html.deleteTag(Tag.B, TagType.OPEN);

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
		html.deleteTags(TagType.CLOSE);

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
		html.deleteTags(TagType.CLOSE, List.of(Tag.B));

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
		html.deleteTag(Tag.A, TagType.CLOSE);

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

	/**
	 * Проверка корректного HTML комментария.
	 */
	@Test
	public void isCommentCorrect()
	{
		assertTrue(new Html("<!---->").isComment());
		assertTrue(new Html("<!-- -->").isComment());
		assertTrue(new Html("<!--  -->").isComment());

		assertTrue(new Html("<!-- 12345 -->").isComment());
		assertTrue(new Html("<!-- Комментарий -->").isComment());
		assertTrue(new Html("<!-- Comment -->").isComment());

		assertTrue(new Html("<!-- Comment-->").isComment());
		assertTrue(new Html("<!--Комментарий -->").isComment());

		assertTrue(new Html("""
			<!--

			-->""").isComment());

		assertTrue(new Html("""
			<!--
				12345
			-->""").isComment());

		assertTrue(new Html("""
			<!--
				12345 -->""").isComment());
	}

	/**
	 * Проверка некорректного HTML комментария.
	 */
	@Test
	public void isCommentNotCorrect()
	{
		assertFalse(new Html("").isComment());
		assertFalse(new Html(" ").isComment());

		assertFalse(new Html("34546<!-- Comment -->").isComment());

		assertFalse(new Html("<!- Комментарий -->").isComment());
		assertFalse(new Html("<-- Комментарий -->").isComment());

		assertFalse(new Html("<!-- Комментарий ->").isComment());
		assertFalse(new Html("<!-- Комментарий --").isComment());

		assertFalse(new Html("<!--< Comment -->").isComment());
		assertFalse(new Html("<!---> Comment -->").isComment());
		assertFalse(new Html("<!-- Comment<!--->").isComment());

		assertFalse(new Html("<!-- Comm<!--ent -->").isComment());
		assertFalse(new Html("<!-- Comm-->ent -->").isComment());
		assertFalse(new Html("<!-- Comm--!>ent -->").isComment());

		assertFalse(new Html("<!-- <!--Comment -->").isComment());
		assertFalse(new Html("<!-- -->Comment -->").isComment());
		assertFalse(new Html("<!-- --!>Comment -->").isComment());

		assertFalse(new Html("<!-- Comment<!-- -->").isComment());
		assertFalse(new Html("<!-- Comment--> -->").isComment());
		assertFalse(new Html("<!-- Comment--!> -->").isComment());

		// Оканчивается на "-->\n", а должно на "-->".
		assertFalse(new Html("""
			<!--
				Comment
			-->
			""").isComment());
	}

	/**
	 * Проверка корректной HTML сущности в виде имени.
	 */
	@Test
	public void isEntityNameCorrect()
	{
		assertTrue(new Html("&dd;").isEntityName());
		assertTrue(new Html("&dollar;").isEntityName());
		assertTrue(new Html("&DownArrowBar;").isEntityName());
		assertTrue(new Html("&ecir;").isEntityName());
		assertTrue(new Html("&DD;").isEntityName());
		assertTrue(new Html("&frac14;").isEntityName());
	}

	/**
	 * Проверка некорректной HTML сущности в виде имени.
	 */
	@Test
	public void isEntityNameNotCorrect()
	{
		assertFalse(new Html("").isEntityName());
		assertFalse(new Html(" ").isEntityName());

		assertFalse(new Html("34546&DownArrowBar;").isEntityName());

		assertFalse(new Html("&").isEntityName());
		assertFalse(new Html(";").isEntityName());
		assertFalse(new Html("&;").isEntityName());
		assertFalse(new Html("&d;").isEntityName());
		assertFalse(new Html("ecir;").isEntityName());
		assertFalse(new Html("&ecir").isEntityName());
		assertFalse(new Html("&3124;").isEntityName());
		assertFalse(new Html("&1DownArrowBar;").isEntityName());
	}

	/**
	 * Проверка корректной HTML сущности в виде числа.
	 */
	@Test
	public void isEntityNumericCorrect()
	{
		assertTrue(new Html("&#038;").isEntityNumeric());
		assertTrue(new Html("&#38;").isEntityNumeric());
		assertTrue(new Html("&#256;").isEntityNumeric());
		assertTrue(new Html("&#0038;").isEntityNumeric());
		assertTrue(new Html("&#8501;").isEntityNumeric());
		assertTrue(new Html("&#10590;").isEntityNumeric());
		assertTrue(new Html("&#010590;").isEntityNumeric());
		assertTrue(new Html("&#0010590;").isEntityNumeric());
		assertTrue(new Html("&#0000000000000010590;").isEntityNumeric());
	}

	/**
	 * Проверка некорректной HTML сущности в виде числа.
	 */
	@Test
	public void isEntityNumericNotCorrect()
	{
		assertFalse(new Html("").isEntityNumeric());
		assertFalse(new Html(" ").isEntityNumeric());

		assertFalse(new Html("34546&#8501;").isEntityNumeric());

		assertFalse(new Html("&#3;").isEntityNumeric());

		assertFalse(new Html("#256;").isEntityNumeric());
		assertFalse(new Html("&256;").isEntityNumeric());
		assertFalse(new Html("&#256").isEntityNumeric());

		assertFalse(new Html("&#2D56;").isEntityNumeric());
	}

	/**
	 * Проверка корректной HTML сущности в виде unicode.
	 */
	@Test
	public void isEntityUnicodeCorrect()
	{
		assertTrue(new Html("&#XB0;").isEntityUnicode());
		assertTrue(new Html("&#x394;").isEntityUnicode());
		assertTrue(new Html("&#X2223;").isEntityUnicode());
		assertTrue(new Html("&#X154;").isEntityUnicode());
		assertTrue(new Html("&#x00000000000000BB;").isEntityUnicode());
	}

	/**
	 * Проверка некорректной HTML сущности в виде unicode.
	 */
	@Test
	public void isEntityUnicodeNotCorrect()
	{
		assertFalse(new Html("").isEntityUnicode());
		assertFalse(new Html(" ").isEntityUnicode());

		assertFalse(new Html("34546&#XB0;").isEntityUnicode());

		assertFalse(new Html("&#x3;").isEntityUnicode());

		assertFalse(new Html("#XB0;").isEntityUnicode());
		assertFalse(new Html("&XB0;").isEntityUnicode());
		assertFalse(new Html("&#B0;").isEntityUnicode());
		assertFalse(new Html("&#B0").isEntityUnicode());
	}

	/**
	 * Проверка корректной типа HTML документа.
	 */
	@Test
	public void isDoctypeCorrect()
	{
		assertTrue(new Html("<!DOCTYPE html>").isDoctype());
		assertTrue(new Html("<!DOCTYPE  html >").isDoctype());

		assertTrue(new Html("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">").isDoctype());
		assertTrue(new Html("<!DOCTYPE HTML PUBLIC \"+//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">").isDoctype());

		assertTrue(new Html("<!DOCTYPE HTML PUBLIC   '-//W3C//DTD HTML 4.01 Transitional//EN' \"http://www.w3.org/TR/html4/loose.dtd\">").isDoctype());
		assertTrue(new Html("<!DOCTYPE HTML  PUBLIC \"-//W3C//DTD HTML 4.01 Frameset//EN\"  'http://www.w3.org/TR/html4/frameset.dtd'>").isDoctype());
		assertTrue(new Html("<!DOCTYPE html  PUBLIC  '-//W3C//DTD XHTML 1.0 Strict//EN'   'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd' >").isDoctype());

		assertTrue(new Html("""
			<!DOCTYPE html PUBLIC
				'-//W3C//DTD XHTML 1.0 Frameset//EN'
			'http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd'
			>""").isDoctype()
		);

		assertTrue(new Html("""
			<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
				"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">""").isDoctype()
		);
	}

	/**
	 * Проверка некорректного типа HTML документа.
	 */
	@Test
	public void isDoctypeNotCorrect()
	{
		assertFalse(new Html("").isDoctype());
		assertFalse(new Html(" ").isDoctype());

		assertFalse(new Html("<DOCTYPE>").isDoctype());

		assertFalse(new Html("<DOCTYPE html>").isDoctype());

		assertFalse(new Html("text<!DOCTYPE html>").isDoctype());

		assertFalse(new Html("<!DOCTYPE HTML PUBLIC>").isDoctype());

		assertFalse(new Html("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"\">").isDoctype());
		assertFalse(new Html("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\">").isDoctype());
		assertFalse(new Html("<!DOCTYPE HTML PUBLIC \"//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">").isDoctype());
		assertFalse(new Html("<!DOCTYPE HTML PUBLIC \"-//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">").isDoctype());
		assertFalse(new Html("<!DOCTYPE HTML PUBLIC \"-//W3C//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">").isDoctype());
		assertFalse(new Html("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01\" \"http://www.w3.org/TR/html4/strict.dtd\">").isDoctype());
		assertFalse(new Html("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//\" \"http://www.w3.org/TR/html4/strict.dtd\">").isDoctype());

		assertFalse(new Html("<!DOCTYPE HTML PUBLIC \"\" \"http://www.w3.org/TR/html4/strict.dtd\">").isDoctype());
		assertFalse(new Html("<!DOCTYPE HTML PUBLIC \"http://www.w3.org/TR/html4/strict.dtd\">").isDoctype());

		assertFalse(new Html("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR'/html4/strict.dtd\">").isDoctype());
		assertFalse(new Html("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" 'http://www.w3.org/TR\"/html4/strict.dtd'>").isDoctype());

		assertFalse(new Html("""
			<!DOCTYPE html PUBLIC
				'-//W3C//DTD XHTML 1.0 Frameset//EN'
			'http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd'
			>
			""").isDoctype()
		);
	}

	/**
	 * Проверка корректного закрывающего HTML тега.
	 */
	@Test
	public void isCloseTagCorrect()
	{
		assertTrue(new Html("</form>").isCloseTag());
		assertTrue(new Html("</form >").isCloseTag());
		assertTrue(new Html("</form  >").isCloseTag());
	}

	/**
	 * Проверка некорректного закрывающего HTML тега.
	 */
	@Test
	public void isCloseTagNotCorrect()
	{
		assertFalse(new Html("").isCloseTag());
		assertFalse(new Html(" ").isCloseTag());

		assertFalse(new Html("</ form>").isCloseTag());
		assertFalse(new Html("< /form>").isCloseTag());

		assertFalse(new Html("text</form>").isCloseTag());
	}

	/**
	 * Проверка корректного самозакрывающегося HTML тега.
	 */
	@Test
	public void isSelfClosingTagCorrect()
	{
		assertTrue(new Html("<meta/>").isSelfClosingTag());
		assertTrue(new Html("<meta />").isSelfClosingTag());

		assertTrue(new Html("<meta charset='UTF-8' />").isSelfClosingTag());
		assertTrue(new Html("<meta charset=\"UTF-8\"/>").isSelfClosingTag());

		assertTrue(new Html("<meta />").isSelfClosingTag());
		assertTrue(new Html("<meta  />").isSelfClosingTag());

		assertTrue(new Html("<meta value/>").isSelfClosingTag());
		assertTrue(new Html("<meta value=yes/>").isSelfClosingTag());
		assertTrue(new Html("<meta value = yes />").isSelfClosingTag());
		assertTrue(new Html("<meta value  =  yes  />").isSelfClosingTag());

		assertTrue(new Html("<input id=''/>").isSelfClosingTag());
		assertTrue(new Html("<input id=\"\"/>").isSelfClosingTag());

		assertTrue(new Html("<meta charset='UTF-8'/>").isSelfClosingTag());
		assertTrue(new Html("<meta charset=\"UTF-8\"/>").isSelfClosingTag());

		assertTrue(new Html("<meta name='123'/>").isSelfClosingTag());
		assertTrue(new Html("<meta name=' текст'/>").isSelfClosingTag());
		assertTrue(new Html("<meta name=' текст 123'/>").isSelfClosingTag());

		assertTrue(new Html("<meta name=\"input name\"/>").isSelfClosingTag());
		assertTrue(new Html("<meta name=\"0123456789\"/>").isSelfClosingTag());

		assertTrue(new Html("<input id='input-id'/>").isSelfClosingTag());
		assertTrue(new Html("<input id=\"input-id-123\"/>").isSelfClosingTag());

		assertTrue(new Html("<input name=\"viewport\" content=\"width=device-width, initial-scale=1, user-scalable=0\"/>").isSelfClosingTag());
		assertTrue(new Html("<input name=viewport content=\"width=device-width, initial-scale=1, user-scalable=0\"/>").isSelfClosingTag());

		assertTrue(new Html("<input id  ='ID' qwe-attr =  \"my_attribute\"  />").isSelfClosingTag());
		assertTrue(new Html("<input qwe_attr =  \"my_attribute\"  />").isSelfClosingTag());

		assertTrue(new Html("""
			<link
				rel="icon"
			type='image/png'
				sizes=""
				href=''/>""").isSelfClosingTag());
	}

	/**
	 * Проверка некорректного самозакрывающегося HTML тега.
	 */
	@Test
	public void isSelfClosingTagNotCorrect()
	{
		assertFalse(new Html("").isSelfClosingTag());
		assertFalse(new Html(" ").isSelfClosingTag());

		assertFalse(new Html("text<input/>").isSelfClosingTag());

		assertFalse(new Html("<link href=/>").isSelfClosingTag());

		assertFalse(new Html("<input name='text\"/>").isSelfClosingTag());

		assertFalse(new Html("<input name=text'/>").isSelfClosingTag());
		assertFalse(new Html("<input name='text/>").isSelfClosingTag());
		assertFalse(new Html("<input name='te'xt'/>").isSelfClosingTag());

		assertFalse(new Html("<input name=text\"/>").isSelfClosingTag());
		assertFalse(new Html("<input name=\"text/>").isSelfClosingTag());
		assertFalse(new Html("<input name=\"te\"xt\"/>").isSelfClosingTag());

		assertFalse(new Html("< input/>").isSelfClosingTag());

		assertFalse(new Html("<input/").isSelfClosingTag());
		assertFalse(new Html("input/>").isSelfClosingTag());

		assertFalse(new Html("<input id=='input-id'/>").isSelfClosingTag());
		assertFalse(new Html("<input id = = 'input-id'/>").isSelfClosingTag());

		assertFalse(new Html("<input id=''input-id'/>").isSelfClosingTag());
		assertFalse(new Html("<input id='input-id''/>").isSelfClosingTag());

		assertFalse(new Html("""
			<link
				rel="icon"
			type='image/png'
				sizes=""
				href=/>
			""").isSelfClosingTag());
	}

	/**
	 * Проверка корректного открывающего HTML тега.
	 */
	@Test
	public void isStartTagCorrect()
	{
		assertTrue(new Html("<form>").isStartTag());
		assertTrue(new Html("<form >").isStartTag());
		assertTrue(new Html("<form  >").isStartTag());

		assertTrue(new Html("<input value>").isStartTag());
		assertTrue(new Html("<input value=yes>").isStartTag());
		assertTrue(new Html("<input value = yes >").isStartTag());
		assertTrue(new Html("<input value  =  yes  >").isStartTag());

		assertTrue(new Html("<input id=''>").isStartTag());
		assertTrue(new Html("<input id=\"\">").isStartTag());

		assertTrue(new Html("<input type=\"checkbox\">").isStartTag());
		assertTrue(new Html("<input type='checkbox'>").isStartTag());

		assertTrue(new Html("<input name='123'>").isStartTag());
		assertTrue(new Html("<input name=' текст'>").isStartTag());
		assertTrue(new Html("<input name=' текст 123'>").isStartTag());

		assertTrue(new Html("<input name=\"input name\">").isStartTag());
		assertTrue(new Html("<input name=\"0123456789\">").isStartTag());

		assertTrue(new Html("<input id='input-id'>").isStartTag());
		assertTrue(new Html("<input id=\"input-id-123\">").isStartTag());

		assertTrue(new Html("<meta charset=\"UTF-8\">").isStartTag());

		assertTrue(new Html("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, user-scalable=0\">").isStartTag());
		assertTrue(new Html("<meta name=viewport content=\"width=device-width, initial-scale=1, user-scalable=0\">").isStartTag());

		assertTrue(new Html("<input id  ='ID' qwe-attr =  \"my_attribute\"  >").isStartTag());
		assertTrue(new Html("<input qwe_attr =  \"my_attribute\"  >").isStartTag());

		assertTrue(new Html("""
			<link
				rel="icon"
			type='image/png'
				sizes=""
				href=''>""").isStartTag());
	}

	/**
	 * Проверка некорректного открывающего HTML тега.
	 */
	@Test
	public void isStartTagNotCorrect()
	{
		assertFalse(new Html("").isStartTag());
		assertFalse(new Html(" ").isStartTag());

		assertFalse(new Html("text<input>").isStartTag());

		assertFalse(new Html("<link href=>").isStartTag());

		assertFalse(new Html("<input name='text\">").isStartTag());

		assertFalse(new Html("<input name=text'>").isStartTag());
		assertFalse(new Html("<input name='text>").isStartTag());
		assertFalse(new Html("<input name='te'xt'>").isStartTag());

		assertFalse(new Html("<input name=text\">").isStartTag());
		assertFalse(new Html("<input name=\"text>").isStartTag());
		assertFalse(new Html("<input name=\"te\"xt\">").isStartTag());

		assertFalse(new Html("< input>").isStartTag());

		assertFalse(new Html("<input").isStartTag());
		assertFalse(new Html("input>").isStartTag());

		assertFalse(new Html("<input id=='input-id'>").isStartTag());
		assertFalse(new Html("<input id = = 'input-id'>").isStartTag());

		assertFalse(new Html("<input id=''input-id'>").isStartTag());
		assertFalse(new Html("<input id='input-id''>").isStartTag());

		assertFalse(new Html("""
			<link
				rel="icon"
			type='image/png'
				sizes=""
				href=>
			""").isStartTag());
	}

	/**
	 * Проверка корректной области CDATA.
	 */
	@Test
	public void isCDataCorrect()
	{
		assertTrue(new Html("<![CDATA[]]>").isCData());
		assertTrue(new Html("<![CDATA[ ]]>").isCData());
		assertTrue(new Html("<![CDATA[  ]]>").isCData());

		assertTrue(new Html("<![CDATA[ 12345 ]]>").isCData());
		assertTrue(new Html("<![CDATA[ Текст ]]>").isCData());
		assertTrue(new Html("<![CDATA[ Text ]]>").isCData());

		assertTrue(new Html("<![CDATA[ Text]]>").isCData());
		assertTrue(new Html("<![CDATA[Текст ]]>").isCData());

		assertTrue(new Html("""
			<![CDATA[

			]]>""").isCData());

		assertTrue(new Html("""
			<![CDATA[
				12345
			]]>""").isCData());

		assertTrue(new Html("""
			<![CDATA[
				12345 ]]>""").isCData());
	}

	/**
	 * Проверка некорректной области CDATA.
	 */
	@Test
	public void isCDataNotCorrect()
	{
		assertFalse(new Html("").isCData());
		assertFalse(new Html(" ").isCData());

		assertFalse(new Html("2134<![CDATA[ Text ]]>").isCData());

		assertFalse(new Html("<[CDATA[ Текст ]]>").isCData());

		assertFalse(new Html("<![CDATA[ Текст ]>").isCData());
		assertFalse(new Html("<![CDATA[ Текст ]]").isCData());

		assertFalse(new Html("<![CDATA[ Te]]>xt ]]>").isCData());
		assertFalse(new Html("<![CDATA[ ]]>Text ]]>").isCData());
		assertFalse(new Html("<![CDATA[ Text]]> ]]>").isCData());

		// Оканчивается на "]]>\n", а должно на "]]>".
		assertFalse(new Html("""
			<![CDATA[
				Text
			]]>
			""").isCData());
	}

	/**
	 * Проверка поиска HTML комментариев.
	 */
	@Test
	public void findComment()
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

		Hipertext html = new Html(text);

		assertArrayEquals(htmlComment, html.findComment().toArray());
	}

	/**
	 * Проверка поиска областей CDATA.
	 */
	@Test
	public void findCData()
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

		Hipertext html = new Html(text);

		assertArrayEquals(cData, html.findCData().toArray());
	}

	/**
	 * Проверка поиска HTML-сущностей в виде имени.
	 */
	@Test
	public void findEntityName()
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

		Hipertext html = new Html(text);

		assertArrayEquals(htmlEntityName, html.findEntityName().toArray());
	}

	/**
	 * Проверка поиска HTML-сущностей в виде числа.
	 */
	@Test
	public void findEntityNumeric()
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

		Hipertext html = new Html(text);

		assertArrayEquals(htmlEntityNumeric, html.findEntityNumeric().toArray());
	}

	/**
	 * Проверка поиска HTML-сущностей в виде unicode.
	 */
	@Test
	public void findEntityUnicode()
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

		Hipertext html = new Html(text);

		assertArrayEquals(htmlEntityUnicode, html.findEntityUnicode().toArray());
	}

	/**
	 * Проверка поиска типов HTML документа.
	 */
	@Test
	public void findDoctype()
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

		Hipertext html = new Html(text);

		assertArrayEquals(htmlDoctype, html.findDoctype().toArray());
	}

	/**
	 * Проверка поиска закрывающих HTML тегов.
	 */
	@Test
	public void testFindCloseTag()
	{
		String[] htmlEndTag = {
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

		Hipertext html = new Html(text);

		assertArrayEquals(htmlEndTag, html.findCloseTag().toArray());
	}

	/**
	 * Проверка поиска открывающих HTML тегов.
	 */
	@Test
	public void findStartTag()
	{
		String[] htmlStartTag = {
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

		Hipertext html = new Html(text);

		assertArrayEquals(htmlStartTag, html.findStartTag().toArray());
	}

	/**
	 * Проверка поиска самозакрывающихся HTML тегов.
	 */
	@Test
	public void findSelfClosingTag()
	{
		String[] htmlStartTag = {
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

		Hipertext html = new Html(text);

		assertArrayEquals(htmlStartTag, html.findSelfClosingTag().toArray());
	}
}