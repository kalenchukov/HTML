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

import dev.kalenchukov.html.types.Entity;
import dev.kalenchukov.html.types.EntityType;
import dev.kalenchukov.html.types.Tag;
import dev.kalenchukov.html.types.TagType;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Класс проверки методов класса {@link Html}.
 *
 * @author Алексей Каленчуков
 */
public class HtmlTest
{
	/**
	 * Проверка метода {@link Html#getText()}.
	 */
	@Test
	public void getText()
	{
		String value = "text 123 текст";
		String expectedString = "text 123 текст";

		Hypertext html = new Html(value);

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#setText(String)}.
	 */
	@Test
	public void setText()
	{
		String value = "text 123 текст";
		String expectedString = "text 123 текст";

		Hypertext html = new Html("");
		html.setText(value);

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#deleteDoctype()}.
	 */
	@Test
	public void deleteDoctype()
	{
		String value = """
			<!DOCTYPE html>
			<b><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
				"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"></b>
			""";

		String expectedString = "<b></b>";
		Hypertext html = new Html(value);
		html.deleteDoctype();

		String actualString = html.getText().trim();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#deleteComments()}.
	 */
	@Test
	public void deleteComments()
	{
		String value = """
			<html><!-- Комментарий --></html>
			<!--
				Комментарий
				с переносом
			-->
			""";

		String expectedString = "<html></html>";

		Hypertext html = new Html(value);
		html.deleteComments();

		String actualString = html.getText().trim();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#deleteCData()}.
	 */
	@Test
	public void deleteCData()
	{
		String value = """
			<html>
			<![CDATA[x<y]]>Текст<![CDATA[
			cdata ->
			text
			]]>
			</html>
			""";

		String expectedString = "<html>\nТекст\n</html>";

		Hypertext html = new Html(value);
		html.deleteCData();

		String actualString = html.getText().trim();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#deleteTag(Tag)}.
	 */
	@Test
	public void deleteTag()
	{
		String value = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<<i>переменный ток/постоянный ток</i>>>) — австралийская <a href = rock.html >рок-группа</a>,<br/>
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года</strong></a> выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом</a> и Ангусом Янгами.<input value = yes />
			""";

		String expectedString = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<<i>переменный ток/постоянный ток</i>>>) — австралийская рок-группа,<br/>
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <strong>1973
			года</strong> выходцами из Шотландии,<br>
			братьями Малькольмом и Ангусом Янгами.<input value = yes />
			""";

		Hypertext html = new Html(value);
		html.deleteTag(Tag.A);

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#deleteTags()}.
	 */
	@Test
	public void deleteTags()
	{
		String value = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<<i>переменный ток/постоянный ток</i>>>) — австралийская <a href = rock.html >рок-группа</a>,<br/>
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года</strong></a> выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом</a> и Ангусом Янгами.<input value = yes />
			""";

		String expectedString = """
			AC/DC (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			<<переменный ток/постоянный ток>>) — австралийская рок-группа,
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии,
			братьями Малькольмом и Ангусом Янгами.
			""";

		Hypertext html = new Html(value);
		html.deleteTags();

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#deleteTags(Set)}.
	 */
	@Test
	public void deleteTagsExclude()
	{
		String value = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<<i>переменный ток/постоянный ток</i>>>) — австралийская <a href = rock.html >рок-группа</a>,<br/>
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года</strong></a> выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом</a> и Ангусом Янгами.<input value = yes />
			""";

		String expectedString = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<переменный ток/постоянный ток>>) — австралийская рок-группа,<br/>
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии,<br>
			братьями Малькольмом и Ангусом Янгами.
			""";

		Hypertext html = new Html(value);
		html.deleteTags(Set.of(Tag.B, Tag.BR));

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#deleteTags(TagType)}.
	 */
	@Test
	public void deleteSelfClosingTags()
	{
		String value = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<<i>переменный ток/постоянный ток</i>>>) — австралийская <a href = rock.html >рок-группа</a>,<br/>
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года</strong></a> выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом</a> и Ангусом Янгами.<input value = yes />
			""";

		String expectedString = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			<<<i>переменный ток/постоянный ток</i>>>) — австралийская <a href = rock.html >рок-группа</a>,
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года</strong></a> выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом</a> и Ангусом Янгами.
			""";

		Hypertext html = new Html(value);
		html.deleteTags(TagType.SELF_CLOSING);

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#deleteTags(TagType, Set)}.
	 */
	@Test
	public void deleteSelfClosingTagsExclude()
	{
		String value = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<<i>переменный ток/постоянный ток</i>>>) — австралийская <a href = rock.html >рок-группа</a>,<br/>
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года</strong></a> выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом</a> и Ангусом Янгами.<input value = yes />
			""";

		String expectedString = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<<i>переменный ток/постоянный ток</i>>>) — австралийская <a href = rock.html >рок-группа</a>,<br/>
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года</strong></a> выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом</a> и Ангусом Янгами.
			""";

		Hypertext html = new Html(value);
		html.deleteTags(TagType.SELF_CLOSING, Set.of(Tag.BR));

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#deleteTag(Tag, TagType)}.
	 */
	@Test
	public void deleteSelfClosingTag()
	{
		String value = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<<i>переменный ток/постоянный ток</i>>>) — австралийская <a href = rock.html >рок-группа</a>,<br/>
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года</strong></a> выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом</a> и Ангусом Янгами.<input value = yes />
			""";

		String expectedString = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			<<<i>переменный ток/постоянный ток</i>>>) — австралийская <a href = rock.html >рок-группа</a>,
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года</strong></a> выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом</a> и Ангусом Янгами.<input value = yes />
			""";

		Hypertext html = new Html(value);
		html.deleteTag(Tag.BR, TagType.SELF_CLOSING);

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#deleteTags(TagType)}.
	 */
	@Test
	public void deleteOpenTags()
	{
		String value = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<<i>переменный ток/постоянный ток</i>>>) — австралийская <a href = rock.html >рок-группа</a>,<br/>
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года</strong></a> выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом</a> и Ангусом Янгами.<input value = yes />
			""";

		String expectedString = """
			AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<переменный ток/постоянный ток</i>>>) — австралийская рок-группа</a>,<br/>
			сформированная в Сиднее в ноябре 1973
			года</strong></a> выходцами из Шотландии,
			братьями Малькольмом</a> и Ангусом Янгами.<input value = yes />
			""";

		Hypertext html = new Html(value);
		html.deleteTags(TagType.OPEN);

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#deleteTags(TagType, Set)}.
	 */
	@Test
	public void deleteOpenTagsExclude()
	{
		String value = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<<i>переменный ток/постоянный ток</i>>>) — австралийская <a href = rock.html >рок-группа</a>,<br/>
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года</strong></a> выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом</a> и Ангусом Янгами.<input value = yes />
			""";

		String expectedString = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<переменный ток/постоянный ток</i>>>) — австралийская рок-группа</a>,<br/>
			сформированная в Сиднее в ноябре 1973
			года</strong></a> выходцами из Шотландии,
			братьями Малькольмом</a> и Ангусом Янгами.<input value = yes />
			""";

		Hypertext html = new Html(value);
		html.deleteTags(TagType.OPEN, Set.of(Tag.B));

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#deleteTag(Tag, TagType)}.
	 */
	@Test
	public void deleteOpenTag()
	{
		String value = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<<i>переменный ток/постоянный ток</i>>>) — австралийская <a href = rock.html >рок-группа</a>,<br/>
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года</strong></a> выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом</a> и Ангусом Янгами.<input value = yes />
			""";

		String expectedString = """
			AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<<i>переменный ток/постоянный ток</i>>>) — австралийская <a href = rock.html >рок-группа</a>,<br/>
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года</strong></a> выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом</a> и Ангусом Янгами.<input value = yes />
			""";

		Hypertext html = new Html(value);
		html.deleteTag(Tag.B, TagType.OPEN);

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#deleteTags(TagType)}.
	 */
	@Test
	public void deleteCloseTags()
	{
		String value = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<<i>переменный ток/постоянный ток</i>>>) — австралийская <a href = rock.html >рок-группа</a>,<br/>
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года</strong></a> выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом</a> и Ангусом Янгами.<input value = yes />
			""";

		String expectedString = """
			<b>AC/DC (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<<i>переменный ток/постоянный ток>>) — австралийская <a href = rock.html >рок-группа,<br/>
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом и Ангусом Янгами.<input value = yes />
			""";

		Hypertext html = new Html(value);
		html.deleteTags(TagType.CLOSE);

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#deleteTags(TagType, Set)}.
	 */
	@Test
	public void deleteCloseTagsExclude()
	{
		String value = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<<i>переменный ток/постоянный ток</i>>>) — австралийская <a href = rock.html >рок-группа</a>,<br/>
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года</strong></a> выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом</a> и Ангусом Янгами.<input value = yes />
			""";

		String expectedString = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<<i>переменный ток/постоянный ток>>) — австралийская <a href = rock.html >рок-группа,<br/>
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом и Ангусом Янгами.<input value = yes />
			""";

		Hypertext html = new Html(value);
		html.deleteTags(TagType.CLOSE, Set.of(Tag.B));

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#deleteTag(Tag, TagType)}.
	 */
	@Test
	public void deleteCloseTag()
	{
		String value = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<<i>переменный ток/постоянный ток</i>>>) — австралийская <a href = rock.html >рок-группа</a>,<br/>
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года</strong></a> выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом</a> и Ангусом Янгами.<input value = yes />
			""";

		String expectedString = """
			<b>AC/DC</b> (эй-си/ди-си; сокращённо от англ. alternating current/direct current<br />
			<<<i>переменный ток/постоянный ток</i>>>) — австралийская <a href = rock.html >рок-группа,<br/>
			сформированная <input disabled value-one = yes>в Сиднее в ноябре <a href="/year" target='_blank' html-tag="t-a-g"><strong>1973
			года</strong> выходцами из Шотландии,<br>
			братьями <a href = 'https://yandex.ru/search/?text=Малькольмом&lr=0' >Малькольмом и Ангусом Янгами.<input value = yes />
			""";

		Hypertext html = new Html(value);
		html.deleteTag(Tag.A, TagType.CLOSE);

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#deleteEntity(Entity, EntityType)}.
	 */
	@Test
	public void deleteEntityName()
	{
		String value = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&#60;переменный ток&#47;постоянный ток&gt;&gt;&rpar; &#8212; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		String expectedString = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&#60;переменный ток&#47;постоянный ток&gt;&gt;&rpar; &#8212; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		Hypertext html = new Html(value);
		html.deleteEntity(Entity.LT, EntityType.NAME);

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#deleteEntity(Entity, EntityType)}.
	 */
	@Test
	public void deleteEntityNumeric()
	{
		String value = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&#60;переменный ток&#47;постоянный ток&gt;&gt;&rpar; &#8212; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		String expectedString = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&lt;переменный ток&#47;постоянный ток&gt;&gt;&rpar; &#8212; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		Hypertext html = new Html(value);
		html.deleteEntity(Entity.LT, EntityType.NUMERIC);

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#deleteEntity(Entity, EntityType)}.
	 */
	@Test
	public void deleteEntityUnicode()
	{
		String value = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&#X3C;переменный ток&#47;постоянный ток&gt;&gt;&rpar; &#8212; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		String expectedString = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&lt;переменный ток&#47;постоянный ток&gt;&gt;&rpar; &#8212; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		Hypertext html = new Html(value);
		html.deleteEntity(Entity.LT, EntityType.UNICODE);

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#deleteEntities()}.
	 */
	@Test
	public void deleteEntities()
	{
		String value = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&lt;переменный ток&#47;постоянный ток&gt;&gt;&rpar; &#8212; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		String expectedString = """
			ACDC эй-сиди-си сокращённо от англ alternating currentdirect current
			переменный токпостоянный ток  австралийская рок-группа
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии
			братьями Малькольмом и Ангусом Янгами
			""";

		Hypertext html = new Html(value);
		html.deleteEntities();

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#deleteEntities(Set)}.
	 */
	@Test
	public void deleteEntitiesExclude()
	{
		String value = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&lt;переменный ток&#47;постоянный ток&gt;&gt;&rpar; &#8212; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		String expectedString = """
			ACDC эй-сиди-си сокращённо от англ alternating currentdirect current
			&lt;&lt;переменный токпостоянный ток&gt;&gt;  австралийская рок-группа
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии
			братьями Малькольмом и Ангусом Янгами
			""";

		Hypertext html = new Html(value);
		html.deleteEntities(Set.of(Entity.LT, Entity.GT));

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#encodeEntity(Entity, EntityType)}.
	 */
	@Test
	public void encodeEntityName()
	{
		String value = """
			AC/DC (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			<<переменный ток/постоянный ток>>) — австралийская рок-группа,
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии,
			братьями Малькольмом и Ангусом Янгами.
			""";

		String expectedString = """
			AC/DC (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			&lt;&lt;переменный ток/постоянный ток>>) — австралийская рок-группа,
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии,
			братьями Малькольмом и Ангусом Янгами.
			""";

		Hypertext html = new Html(value);
		html.encodeEntity(Entity.LT, EntityType.NAME);

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#encodeEntity(Entity, EntityType)}.
	 */
	@Test
	public void encodeEntityNumeric()
	{
		String value = """
			AC/DC (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			<<переменный ток/постоянный ток>>) — австралийская рок-группа,
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии,
			братьями Малькольмом и Ангусом Янгами.
			""";

		String expectedString = """
			AC/DC (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			&#60;&#60;переменный ток/постоянный ток>>) — австралийская рок-группа,
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии,
			братьями Малькольмом и Ангусом Янгами.
			""";

		Hypertext html = new Html(value);
		html.encodeEntity(Entity.LT, EntityType.NUMERIC);

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#encodeEntity(Entity, EntityType)}.
	 */
	@Test
	public void encodeEntityUnicode()
	{
		String value = """
			AC/DC (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			<<переменный ток/постоянный ток>>) — австралийская рок-группа,
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии,
			братьями Малькольмом и Ангусом Янгами.
			""";

		String expectedString = """
			AC/DC (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			&#X3C;&#X3C;переменный ток/постоянный ток>>) — австралийская рок-группа,
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии,
			братьями Малькольмом и Ангусом Янгами.
			""";

		Hypertext html = new Html(value);
		html.encodeEntity(Entity.LT, EntityType.UNICODE);

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#encodeEntities(EntityType)}.
	 */
	@Test
	public void encodeEntitiesName()
	{
		String value = """
			AC/DC (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			<<переменный ток/постоянный ток>>) — австралийская рок-группа,
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии,
			братьями Малькольмом и Ангусом Янгами.
			""";

		String expectedString = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&lt;переменный ток&sol;постоянный ток&gt;&gt;&rpar; &mdash; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&period;
			""";

		Hypertext html = new Html(value);
		html.encodeEntities(EntityType.NAME);

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#encodeEntities(EntityType)}.
	 */
	@Test
	public void encodeEntitiesNumeric()
	{
		String value = """
			AC/DC (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			<<переменный ток/постоянный ток>>) — австралийская рок-группа,
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии,
			братьями Малькольмом и Ангусом Янгами.
			""";

		String expectedString = """
			AC&#47;DC &#40;эй-си&#47;ди-си&#59; сокращённо от англ&#46; alternating current&#47;direct current
			&#60;&#60;переменный ток&#47;постоянный ток&#62;&#62;&#41; &#8212; австралийская рок-группа&#44;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&#44;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		Hypertext html = new Html(value);
		html.encodeEntities(EntityType.NUMERIC);

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#encodeEntities(EntityType)}.
	 */
	@Test
	public void encodeEntitiesUnicode()
	{
		String value = """
			AC/DC (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			<<переменный ток/постоянный ток>>) — австралийская рок-группа,
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии,
			братьями Малькольмом и Ангусом Янгами.
			""";

		String expectedString = """
			AC&#X2F;DC &#X28;эй-си&#X2F;ди-си&#X3B; сокращённо от англ&#X2E; alternating current&#X2F;direct current
			&#X3C;&#X3C;переменный ток&#X2F;постоянный ток&#X3E;&#X3E;&#X29; &#X2014; австралийская рок-группа&#X2C;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&#X2C;
			братьями Малькольмом и Ангусом Янгами&#X2E;
			""";

		Hypertext html = new Html(value);
		html.encodeEntities(EntityType.UNICODE);

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#encodeEntities(EntityType, Set)}.
	 */
	@Test
	public void encodeEntitiesNameExclude()
	{
		String value = """
			AC/DC (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			<<переменный ток/постоянный ток>>) — австралийская рок-группа,
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии,
			братьями Малькольмом и Ангусом Янгами.
			""";

		String expectedString = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			<<переменный ток&sol;постоянный ток>>&rpar; &mdash; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&period;
			""";

		Hypertext html = new Html(value);
		html.encodeEntities(EntityType.NAME, Set.of(Entity.LT, Entity.GT));

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#encodeEntities(EntityType, Set)}.
	 */
	@Test
	public void encodeEntitiesNumericExclude()
	{
		String value = """
			AC/DC (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			<<переменный ток/постоянный ток>>) — австралийская рок-группа,
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии,
			братьями Малькольмом и Ангусом Янгами.
			""";

		String expectedString = """
			AC&#47;DC &#40;эй-си&#47;ди-си&#59; сокращённо от англ&#46; alternating current&#47;direct current
			<<переменный ток&#47;постоянный ток>>&#41; &#8212; австралийская рок-группа&#44;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&#44;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		Hypertext html = new Html(value);
		html.encodeEntities(EntityType.NUMERIC, Set.of(Entity.LT, Entity.GT));

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#encodeEntities(EntityType, Set)}.
	 */
	@Test
	public void encodeEntitiesUnicodeExclude()
	{
		String value = """
			AC/DC (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			<<переменный ток/постоянный ток>>) — австралийская рок-группа,
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии,
			братьями Малькольмом и Ангусом Янгами.
			""";

		String expectedString = """
			AC&#X2F;DC &#X28;эй-си&#X2F;ди-си&#X3B; сокращённо от англ&#X2E; alternating current&#X2F;direct current
			<<переменный ток&#X2F;постоянный ток>>&#X29; &#X2014; австралийская рок-группа&#X2C;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&#X2C;
			братьями Малькольмом и Ангусом Янгами&#X2E;
			""";

		Hypertext html = new Html(value);
		html.encodeEntities(EntityType.UNICODE, Set.of(Entity.LT, Entity.GT));

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#decodeEntity(Entity)}.
	 */
	@Test
	public void decodeEntity()
	{
		String value = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&lt;переменный ток&#47;постоянный ток&gt;&gt;&rpar; &#8212; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		String expectedString = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			<<переменный ток&#47;постоянный ток&gt;&gt;&rpar; &#8212; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		Hypertext html = new Html(value);
		html.decodeEntity(Entity.LT);

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#decodeEntity(Entity, EntityType)}.
	 */
	@Test
	public void decodeEntityName()
	{
		String value = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&lt;переменный ток&#47;постоянный ток&gt;&gt;&rpar; &#8212; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		String expectedString = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			<<переменный ток&#47;постоянный ток&gt;&gt;&rpar; &#8212; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		Hypertext html = new Html(value);
		html.decodeEntity(Entity.LT, EntityType.NAME);

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#decodeEntity(Entity, EntityType)}.
	 */
	@Test
	public void decodeEntityNumeric()
	{
		String value = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&lt;переменный ток&#47;постоянный ток&gt;&gt;&rpar; &#8212; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		String expectedString = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&lt;переменный ток&#47;постоянный ток&gt;&gt;&rpar; — австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		Hypertext html = new Html(value);
		html.decodeEntity(Entity.MDASH, EntityType.NUMERIC);

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#decodeEntity(Entity, EntityType)}.
	 */
	@Test
	public void decodeEntityUnicode()
	{
		String value = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&lt;переменный ток&#47;постоянный ток&gt;&gt;&rpar; &#X2014; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		String expectedString = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&lt;переменный ток&#47;постоянный ток&gt;&gt;&rpar; — австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		Hypertext html = new Html(value);
		html.decodeEntity(Entity.MDASH, EntityType.UNICODE);

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#decodeEntities()}.
	 */
	@Test
	public void decodeEntities()
	{
		String value = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&lt;переменный ток&#47;постоянный ток&gt;&gt;&rpar; &#8212; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#46;
			""";

		String expectedString = """
			AC/DC (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			<<переменный ток/постоянный ток>>) — австралийская рок-группа,
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии,
			братьями Малькольмом и Ангусом Янгами.
			""";

		Hypertext html = new Html(value);
		html.decodeEntities();

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#decodeEntities(EntityType)}.
	 */
	@Test
	public void decodeEntitiesName()
	{
		String value = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&#60;&#x3C;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;&rpar; &mdash; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&period;
			""";

		String expectedString = """
			AC/DC (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			<&#60;&#x3C;переменный ток/постоянный ток&#62;&#X0003E;>) — австралийская рок-группа,
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии,
			братьями Малькольмом и Ангусом Янгами.
			""";

		Hypertext html = new Html(value);
		html.decodeEntities(EntityType.NAME);

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#decodeEntities(EntityType)}.
	 */
	@Test
	public void decodeEntitiesNumeric()
	{
		String value = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&#59; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&#60;&#x3C;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;&rpar; &#x2014; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&period;
			""";

		String expectedString = """
			AC&sol;DC &lpar;эй-си&sol;ди-си; сокращённо от англ&period; alternating current&sol;direct current
			&lt;<&#x3C;переменный ток&sol;постоянный ток>&#X0003E;&gt;&rpar; &#x2014; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&period;
			""";

		Hypertext html = new Html(value);
		html.decodeEntities(EntityType.NUMERIC);

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#decodeEntities(EntityType)}.
	 */
	@Test
	public void decodeEntitiesUnicode()
	{
		String value = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&#59; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&#60;&#x3C;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;&rpar; &#x2014; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#X00002E;
			""";

		String expectedString = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&#59; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&#60;<переменный ток&sol;постоянный ток&#62;>&gt;&rpar; — австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами.
			""";

		Hypertext html = new Html(value);
		html.decodeEntities(EntityType.UNICODE);

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#decodeEntities(Set)}.
	 */
	@Test
	public void decodeEntitiesExclude()
	{
		String value = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&#60;&#x3C;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;&rpar; &mdash; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&period;
			""";

		String expectedString = """
			AC/DC (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			&lt;&#60;&#x3C;переменный ток/постоянный ток&#62;&#X0003E;&gt;) — австралийская рок-группа,
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии,
			братьями Малькольмом и Ангусом Янгами.
			""";

		Hypertext html = new Html(value);
		html.decodeEntities(Set.of(Entity.LT, Entity.GT));

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#decodeEntities(EntityType, Set)}.
	 */
	@Test
	public void decodeEntitiesNameExclude()
	{
		String value = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&semi; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&#60;&#x3C;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;&rpar; &mdash; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&period;
			""";

		String expectedString = """
			AC/DC (эй-си/ди-си; сокращённо от англ. alternating current/direct current
			&lt;&#60;&#x3C;переменный ток/постоянный ток&#62;&#X0003E;&gt;) — австралийская рок-группа,
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии,
			братьями Малькольмом и Ангусом Янгами.
			""";

		Hypertext html = new Html(value);
		html.decodeEntities(EntityType.NAME, Set.of(Entity.LT, Entity.GT));

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#decodeEntities(EntityType, Set)}.
	 */
	@Test
	public void decodeEntitiesNumericExclude()
	{
		String value = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&#59; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&#60;&#x3C;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;&rpar; &#x2014; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&period;
			""";

		String expectedString = """
			AC&sol;DC &lpar;эй-си&sol;ди-си; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&#60;&#x3C;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;&rpar; &#x2014; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&period;
			""";

		Hypertext html = new Html(value);
		html.decodeEntities(EntityType.NUMERIC, Set.of(Entity.LT, Entity.GT));

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Проверка метода {@link Html#decodeEntities(EntityType, Set)}.
	 */
	@Test
	public void decodeEntitiesUnicodeExclude()
	{
		String value = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&#59; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&#60;&#x3C;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;&rpar; &#x2014; австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами&#X00002E;
			""";

		String expectedString = """
			AC&sol;DC &lpar;эй-си&sol;ди-си&#59; сокращённо от англ&period; alternating current&sol;direct current
			&lt;&#60;&#x3C;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;&rpar; — австралийская рок-группа&comma;
			сформированная в Сиднее в ноябре 1973
			года выходцами из Шотландии&comma;
			братьями Малькольмом и Ангусом Янгами.
			""";

		Hypertext html = new Html(value);
		html.decodeEntities(EntityType.UNICODE, Set.of(Entity.LT, Entity.GT));

		String actualString = html.getText();

		assertThat(actualString).isEqualTo(expectedString);
	}

	/**
	 * Класс проверки метода {@link Html#isComment()}.
	 *
	 * @author Алексей Каленчуков
	 */
	@Nested
	public class IsComment
	{
		/**
		 * Проверка метода {@link Html#isComment()}.
		 */
		@ParameterizedTest
		@ValueSource(strings = {
			"<!---->",
			"<!-- -->",
			"<!--  -->",
			"<!-- 12345 -->",
			"<!-- Комментарий -->",
			"<!-- Comment -->",
			"<!-- Comment-->",
			"<!--Комментарий -->",
			"<!--\n\t12345\n-->",
			"<!--\n12345 -->"
		})
		public void isComment(String value)
		{
			Hypertext html = new Html(value);

			boolean actual = html.isComment();

			assertThat(actual).isTrue();
		}

		/**
		 * Проверка метода {@link Html#isComment()} с некорректным значением.
		 */
		@ParameterizedTest
		@ValueSource(strings = {
			"",
			" ",
			"34546<!-- Comment -->",
			"<!- Комментарий -->",
			"<-- Комментарий -->",
			"<!-- Комментарий ->",
			"<!-- Комментарий --",
			"<!--< Comment -->",
			"<!---> Comment -->",
			"<!-- Comment<!--->",
			"<!-- Comm<!--ent -->",
			"<!-- Comm-->ent -->",
			"<!-- Comm--!>ent -->",
			"<!-- <!--Comment -->",
			"<!-- -->Comment -->",
			"<!-- --!>Comment -->",
			"<!-- Comment<!-- -->",
			"<!-- Comment--> -->",
			"<!-- Comment--!> -->",
			"<!--\n\tComment\n-->\n"
		})
		public void isCommentNotCorrect(String value)
		{
			Hypertext html = new Html(value);

			boolean actual = html.isComment();

			assertThat(actual).isFalse();
		}
	}

	/**
	 * Класс проверки метода {@link Html#isEntity()}.
	 *
	 * @author Алексей Каленчуков
	 */
	@Nested
	public class IsEntity
	{
		/**
		 * Проверка метода {@link Html#isEntity()}.
		 */
		@ParameterizedTest
		@ValueSource(strings = {
			"&dd;",
			"&dollar;",
			"&DownArrowBar;",
			"&ecir;",
			"&DD;",
			"&frac14;",
			"&#038;",
			"&#38;",
			"&#256;",
			"&#0038;",
			"&#8501;",
			"&#10590;",
			"&#010590;",
			"&#0010590;",
			"&#0000000000000010590;",
			"&#XB0;",
			"&#x394;",
			"&#X2223;",
			"&#X154;",
			"&#x00000000000000BB;"
		})
		public void isEntity(String value)
		{
			Hypertext html = new Html(value);

			boolean actual = html.isEntity();

			assertThat(actual).isTrue();
		}

		/**
		 * Проверка метода {@link Html#isEntity()}.
		 */
		@ParameterizedTest
		@ValueSource(strings = {
			"",
			" ",
			"34546&DownArrowBar;",
			"&",
			";",
			"&;",
			"&d;",
			"ecir;",
			"&ecir",
			"&3124;",
			"&1DownArrowBar;",
			"34546&#8501;",
			"&#3;",
			"#256;",
			"&256;",
			"&#256",
			"&#2D56;",
			"34546&#XB0;",
			"&#x3;",
			"#XB0;",
			"&#B0;",
			"&#B0"
		})
		public void isEntityNotCorrect(String value)
		{
			Hypertext html = new Html(value);

			boolean actual = html.isEntity();

			assertThat(actual).isFalse();
		}
	}

	/**
	 * Класс проверки метода {@link Html#isEntityName()}.
	 *
	 * @author Алексей Каленчуков
	 */
	@Nested
	public class IsEntityName
	{
		/**
		 * Проверка метода {@link Html#isEntityName()}.
		 */
		@ParameterizedTest
		@ValueSource(strings = {
			"&dd;", "&dollar;", "&DownArrowBar;", "&ecir;", "&DD;", "&frac14;"
		})
		public void isEntityName(String value)
		{
			Hypertext html = new Html(value);

			boolean actual = html.isEntityName();

			assertThat(actual).isTrue();
		}

		/**
		 * Проверка метода {@link Html#isEntityName} с некорректным значением.
		 */
		@ParameterizedTest
		@ValueSource(strings = {
			"", " ", "34546&DownArrowBar;", "&", ";", "&;", "&d;", "ecir;", "&ecir", "&3124;", "&1DownArrowBar;"
		})
		public void isEntityNameNotCorrect(String value)
		{
			Hypertext html = new Html(value);

			boolean actual = html.isEntityName();

			assertThat(actual).isFalse();
		}
	}

	/**
	 * Класс проверки метода {@link Html#isEntityNumeric()}.
	 *
	 * @author Алексей Каленчуков
	 */
	@Nested
	public class IsEntityNumeric
	{
		/**
		 * Проверка метода {@link Html#isEntityNumeric()}.
		 */
		@ParameterizedTest
		@ValueSource(strings = {
			"&#038;",
			"&#38;",
			"&#256;",
			"&#0038;",
			"&#8501;",
			"&#10590;",
			"&#010590;",
			"&#0010590;",
			"&#0000000000000010590;"
		})
		public void isEntityNumeric(String value)
		{
			Hypertext html = new Html(value);

			boolean actual = html.isEntityNumeric();

			assertThat(actual).isTrue();
		}

		/**
		 * Проверка метода {@link Html#isEntityNumeric} с некорректным значением.
		 */
		@ParameterizedTest
		@ValueSource(strings = {
			"", " ", "34546&#8501;", "&#3;", "#256;", "&256;", "&#256", "&#2D56;"
		})
		public void isEntityNumericNotCorrect(String value)
		{
			Hypertext html = new Html(value);

			boolean actual = html.isEntityNumeric();

			assertThat(actual).isFalse();
		}
	}

	/**
	 * Класс проверки метода {@link Html#isEntityUnicode()}.
	 *
	 * @author Алексей Каленчуков
	 */
	@Nested
	public class IsEntityUnicode
	{
		/**
		 * Проверка метода {@link Html#isEntityUnicode()}.
		 */
		@ParameterizedTest
		@ValueSource(strings = {
			"&#XB0;", "&#x394;", "&#X2223;", "&#X154;", "&#x00000000000000BB;"
		})
		public void isEntityUnicode(String value)
		{
			Hypertext html = new Html(value);

			boolean actual = html.isEntityUnicode();

			assertThat(actual).isTrue();
		}

		/**
		 * Проверка метода {@link Html#isEntityUnicode()} с некорректным значением.
		 */
		@ParameterizedTest
		@ValueSource(strings = {
			"", " ", "34546&#XB0;", "&#x3;", "#XB0;", "&XB0;", "&#B0;", "&#B0"
		})
		public void isEntityUnicodeNotCorrect(String value)
		{
			Hypertext html = new Html(value);

			boolean actual = html.isEntityUnicode();

			assertThat(actual).isFalse();
		}
	}

	/**
	 * Класс проверки метода {@link Html#isDoctype()}.
	 *
	 * @author Алексей Каленчуков
	 */
	@Nested
	public class IsDoctype
	{
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
		public void isDoctype(String value)
		{
			Hypertext html = new Html(value);

			boolean actual = html.isDoctype();

			assertThat(actual).isTrue();
		}

		/**
		 * Проверка метода {@link Html#isDoctype()} с некорректным значением.
		 */
		@ParameterizedTest
		@ValueSource(strings = {
			"",
			" ",
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
		public void isDoctypeNotCorrect(String value)
		{
			Hypertext html = new Html(value);

			boolean actual = html.isDoctype();

			assertThat(actual).isFalse();
		}
	}

	/**
	 * Класс проверки метода {@link Html#isTag()}.
	 *
	 * @author Алексей Каленчуков
	 */
	@Nested
	public class IsTag
	{
		/**
		 * Проверка метода {@link Html#isTag()}.
		 */
		@ParameterizedTest
		@ValueSource(strings = {
			"<form>",
			"<b >",
			"<br  >",
			"<input value>",
			"<input value=yes>",
			"</form>",
			"</form >",
			"</form  >",
			"<meta/>",
			"<meta />",
			"<meta charset='UTF-8' />",
			"<meta charset=\"UTF-8\"/>"

		})
		public void isTag(String value)
		{
			Hypertext html = new Html(value);

			boolean actual = html.isTag();

			assertThat(actual).isTrue();
		}

		/**
		 * Проверка метода {@link Html#isTag()} с некорректным значением.
		 */
		@ParameterizedTest
		@ValueSource(strings = {
			"",
			" ",
			"text<input>",
			"<link href=>",
			"<input name='text\">",
			"</ form>",
			"< /form>",
			"text</form>",
			"text<input/>",
			"<input name='text\"/>"
		})
		public void isTagNotCorrect(String value)
		{
			Hypertext html = new Html(value);

			boolean actual = html.isTag();

			assertThat(actual).isFalse();
		}
	}

	/**
	 * Класс проверки метода {@link Html#isCloseTag()}.
	 *
	 * @author Алексей Каленчуков
	 */
	@Nested
	public class IsCloseTag
	{
		/**
		 * Проверка метода {@link Html#isCloseTag()}.
		 */
		@ParameterizedTest
		@ValueSource(strings = {
			"</form>", "</form >", "</form  >"
		})
		public void isCloseTag(String value)
		{
			Hypertext html = new Html(value);

			boolean actual = html.isCloseTag();

			assertThat(actual).isTrue();
		}

		/**
		 * Проверка метода {@link Html#isCloseTag()}.
		 */
		@ParameterizedTest
		@ValueSource(strings = {
			"", " ", "</ form>", "< /form>", "text</form>"
		})
		public void isCloseTagNotCorrect(String value)
		{
			Hypertext html = new Html(value);

			boolean actual = html.isCloseTag();

			assertThat(actual).isFalse();
		}
	}

	/**
	 * Класс проверки метода {@link Html#isSelfClosingTag()}.
	 *
	 * @author Алексей Каленчуков
	 */
	@Nested
	public class IsSelfClosingTag
	{
		/**
		 * Проверка метода {@link Html#isSelfClosingTag()}.
		 */
		@ParameterizedTest
		@ValueSource(strings = {
			"<meta/>",
			"<meta />",
			"<meta charset='UTF-8' />",
			"<meta charset=\"UTF-8\"/>",
			"<meta />",
			"<meta  />",
			"<meta value/>",
			"<meta value=yes/>",
			"<meta value = yes />",
			"<meta value  =  yes  />",
			"<input id=''/>",
			"<input id=\"\"/>",
			"<meta charset='UTF-8'/>",
			"<meta charset=\"UTF-8\"/>",
			"<meta name='123'/>",
			"<meta name=' текст'/>",
			"<meta name=' текст 123'/>",
			"<meta name=\"input name\"/>",
			"<meta name=\"0123456789\"/>",
			"<input id='input-id'/>",
			"<input id=\"input-id-123\"/>",
			"<input name=\"viewport\" content=\"width=device-width, initial-scale=1, user-scalable=0\"/>",
			"<input name=viewport content=\"width=device-width, initial-scale=1, user-scalable=0\"/>",
			"<input id  ='ID' qwe-attr =  \"my_attribute\"  />",
			"<input qwe_attr =  \"my_attribute\"  />",
			"<link\n\trel=\"icon\"\ntype='image/png'\n\tsizes=\"\"\n\thref=''/>"
		})
		public void isSelfClosingTag(String value)
		{
			Hypertext html = new Html(value);

			boolean actual = html.isSelfClosingTag();

			assertThat(actual).isTrue();
		}

		/**
		 * Проверка метода {@link Html#isSelfClosingTag()} с некорректным значением.
		 */
		@ParameterizedTest
		@ValueSource(strings = {
			"",
			" ",
			"text<input/>",
			"<link href=/>",
			"<input name='text\"/>",
			"<input name=text'/>",
			"<input name='text/>",
			"<input name='te'xt'/>",
			"<input name=text\"/>",
			"<input name=\"text/>",
			"<input name=\"te\"xt\"/>",
			"< input/>",
			"<input/",
			"input/>",
			"<input id=='input-id'/>",
			"<input id = = 'input-id'/>",
			"<input id=''input-id'/>",
			"<input id='input-id''/>",
			"<link\n\trel=\"icon\"\ntype='image/png'\n\tsizes=\"\"\n\thref=/>"
		})
		public void isSelfClosingTagNotCorrect(String value)
		{
			Hypertext html = new Html(value);

			boolean actual = html.isSelfClosingTag();

			assertThat(actual).isFalse();
		}
	}

	/**
	 * Класс проверки метода {@link Html#isOpenTag()}.
	 *
	 * @author Алексей Каленчуков
	 */
	@Nested
	public class IsOpenTag
	{
		/**
		 * Проверка метода {@link Html#isOpenTag()}.
		 */
		@ParameterizedTest
		@ValueSource(strings = {
			"<form>",
			"<form >",
			"<form  >",
			"<input value>",
			"<input value=yes>",
			"<input value = yes >",
			"<input value  =  yes  >",
			"<input id=''>",
			"<input id=\"\">",
			"<input type=\"checkbox\">",
			"<input type='checkbox'>",
			"<input name='123'>",
			"<input name=' текст'>",
			"<input name=' текст 123'>",
			"<input name=\"input name\">",
			"<input name=\"0123456789\">",
			"<input id='input-id'>",
			"<input id=\"input-id-123\">",
			"<meta charset=\"UTF-8\">",
			"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, user-scalable=0\">",
			"<meta name=viewport content=\"width=device-width, initial-scale=1, user-scalable=0\">",
			"<input id  ='ID' qwe-attr =  \"my_attribute\"  >",
			"<input qwe_attr =  \"my_attribute\"  >",
			"<link\n\trel=\"icon\"\ntype='image/png'\n\tsizes=\"\"\nhref=''>"
		})
		public void isOpenTag(String value)
		{
			Hypertext html = new Html(value);

			boolean actual = html.isOpenTag();

			assertThat(actual).isTrue();
		}

		/**
		 * Проверка метода {@link Html#isOpenTag()} с некорректным значением.
		 */
		@ParameterizedTest
		@ValueSource(strings = {
			"",
			" ",
			"<br/>",
			"<input value =  yes/>",
			"</b>",
			"text<input>",
			"<link href=>",
			"<input name='text\">",
			"<input name=text'>",
			"<input name='text>",
			"<input name='te'xt'>",
			"<input name=text\">",
			"<input name=\"text>",
			"<input name=\"te\"xt\">",
			"< input>",
			"<input",
			"input>",
			"<input id=='input-id'>",
			"<input id = = 'input-id'>",
			"<input id=''input-id'>",
			"<input id='input-id''>",
			"<link\n\trel=\"icon\"\ntype='image/png'\n\tsizes=\"\"\n\thref=>"
		})
		public void isOpenTagNotCorrect(String value)
		{
			Hypertext html = new Html(value);

			boolean actual = html.isOpenTag();

			assertThat(actual).isFalse();
		}
	}

	/**
	 * Класс проверки метода {@link Html#isCData()}.
	 *
	 * @author Алексей Каленчуков
	 */
	@Nested
	public class IsCData
	{
		/**
		 * Проверка метода {@link Html#isCData()}.
		 */
		@ParameterizedTest
		@ValueSource(strings = {
			"<![CDATA[]]>",
			"<![CDATA[ ]]>",
			"<![CDATA[  ]]>",
			"<![CDATA[ 12345 ]]>",
			"<![CDATA[ Текст ]]>",
			"<![CDATA[ Text ]]>",
			"<![CDATA[ Text]]>",
			"<![CDATA[Текст ]]>",
			"<![CDATA[\n\n]]>",
			"<![CDATA[\n\t12345\n]]>",
			"<![CDATA[\n\t12345 ]]>"
		})
		public void isCData(String value)
		{
			Hypertext html = new Html(value);

			boolean actual = html.isCData();

			assertThat(actual).isTrue();
		}

		/**
		 * Проверка метода {@link Html#isCData()} с некорректным значением.
		 */
		@ParameterizedTest
		@ValueSource(strings = {
			"",
			" ",
			"2134<![CDATA[ Text ]]>",
			"<[CDATA[ Текст ]]>",
			"<![CDATA[ Текст ]>",
			"<![CDATA[ Текст ]]",
			"<![CDATA[ Te]]>xt ]]>",
			"<![CDATA[ ]]>Text ]]>",
			"<![CDATA[ Text]]> ]]>",
			"<![CDATA[\n\tText\n]]>\n"
		})
		public void isCDataNotCorrect(String value)
		{
			Hypertext html = new Html(value);

			boolean actual = html.isCData();

			assertThat(actual).isFalse();
		}
	}

	/**
	 * Проверка метода {@link Html#findComments()}.
	 */
	@Test
	public void findComments()
	{
		String value = """
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

		List<String> expectedList = List.of(
				"<!--Всё в свое время, зима и весна-->",
				"<!-- яблоку место упасть\nКаждому вору возможность украсть\nКаждой собаке палку и кость-->",
				"<!--,-->",
				"<!---->"
		);

		Hypertext html = new Html(value);

		List<String> actualList = html.findComments();

		assertThat(actualList).containsSequence(expectedList);
	}

	/**
	 * Проверка метода {@link Html#findCData()}.
	 */
	@Test
	public void findCData()
	{
		String value = """
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

		List<String> expectedList = List.of(
				"<![CDATA[Здесь не понятно, где лицо, а где рыло,]]>",
				"<![CDATA[Здесь в сено не втыкаются вилы,\nА рыба проходит сквозь сеть.\nИ не ясно, где море, где суша,\nГде золото, а где медь.]]>",
				"<![CDATA[...]]>",
				"<![CDATA[]]>"
		);

		Hypertext html = new Html(value);

		List<String> actualList = html.findCData();

		assertThat(actualList).containsSequence(expectedList);
	}

	/**
	 * Проверка метода {@link Html#findEntities()}.
	 */
	@Test
	public void findEntities()
	{
		String value = """
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

		List<String> expectedList = List.of(
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
		);

		Hypertext html = new Html(value);

		List<String> actualList = html.findEntities();

		assertThat(actualList).containsSequence(expectedList);
	}

	/**
	 * Проверка метода {@link Html#findEntitiesName()}.
	 */
	@Test
	public void findEntitiesName()
	{
		String value = """
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

		List<String> expectedList = List.of(
				"&frac14;",
				"&amp;",
				"&commat;",
				"&lt;"
		);

		Hypertext html = new Html(value);

		List<String> actualList = html.findEntitiesName();

		assertThat(actualList).containsSequence(expectedList);
	}

	/**
	 * Проверка метода {@link Html#findEntitiesNumeric()}.
	 */
	@Test
	public void findEntitiesNumeric()
	{
		String value = """
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

		List<String> expectedList = List.of(
				"&#44;",
				"&#169;",
				"&#10589;",
				"&#10590;"
		);

		Hypertext html = new Html(value);

		List<String> actualList = html.findEntitiesNumeric();

		assertThat(actualList).containsSequence(expectedList);
	}

	/**
	 * Проверка метода {@link Html#findEntitiesUnicode()}.
	 */
	@Test
	public void findEntitiesUnicode()
	{
		String value = """
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

		List<String> expectedList = List.of(
				"&#X22C8;",
				"&#x224E;",
				"&#X0000141;",
				"&#X000000BB;"
		);

		Hypertext html = new Html(value);

		List<String> actualList = html.findEntitiesUnicode();

		assertThat(actualList).containsSequence(expectedList);
	}

	/**
	 * Проверка метода {@link Html#findDoctype()}.
	 */
	@Test
	public void findDoctype()
	{
		String value = """
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

		List<String> expectedList = List.of(
				"<!DOCTYPE html>",
				"<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Strict//EN'\n'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd'>",
				"<!DOCTYPE html PUBLIC \"+//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">",
				"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" 'http://www.w3.org/TR/html4/loose.dtd'>"
		);

		Hypertext html = new Html(value);

		List<String> actualList = html.findDoctype();

		assertThat(actualList).containsSequence(expectedList);
	}

	/**
	 * Проверка метода {@link Html#findTags()}.
	 */
	@Test
	public void findTags()
	{
		String value = """
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

		List<String> expectedList = List.of(
				"<b>",
				"<meta charset='UTF-8'>",
				"<input type='checkbox'  >",
				"<input value =  yes>",

				"<br/>",
				"<meta charset='UTF-8'/>",
				"<input type='checkbox'  />",
				"<input value =  yes/>",

				"</b>",
				"</b >",
				"</table>",
				"</strong>"
		);

		Hypertext html = new Html(value);

		List<String> actualList = html.findTags();

		assertThat(actualList).containsSequence(expectedList);
	}

	/**
	 * Проверка метода {@link Html#findCloseTags()}.
	 */
	@Test
	public void findCloseTags()
	{
		String value = """
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

		List<String> expectedList = List.of(
				"</form>",
				"</b >",
				"</table>",
				"</strong>"
		);

		Hypertext html = new Html(value);

		List<String> actualList = html.findCloseTags();

		assertThat(actualList).containsSequence(expectedList);
	}

	/**
	 * Проверка метода {@link Html#findOpenTags()}.
	 */
	@Test
	public void findOpenTags()
	{
		String value = """
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

		List<String> expectedList = List.of(
				"<b>",
				"<meta charset='UTF-8'>",
				"<input type='checkbox'  >",
				"<input value =  yes>"
		);

		Hypertext html = new Html(value);

		List<String> actualList = html.findOpenTags();

		assertThat(actualList).containsSequence(expectedList);
	}

	/**
	 * Проверка метода {@link Html#findSelfClosingTags()}.
	 */
	@Test
	public void findSelfClosingTags()
	{
		String value = """
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

		List<String> expectedList = List.of(
				"<br/>",
				"<meta charset='UTF-8'/>",
				"<input type='checkbox'  />",
				"<input value =  yes/>"
		);

		Hypertext html = new Html(value);

		List<String> actualList = html.findSelfClosingTags();

		assertThat(actualList).containsSequence(expectedList);
	}
}