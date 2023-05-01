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

import org.jetbrains.annotations.NotNull;

/**
 * Перечисление HTML-тегов.
 *
 * @author Aleksey Kalenchukov
 */
public enum Tag
{
	/**
	 * Тег &#60;HTML&#62;.
	 */
	HTML("html"),

	/**
	 * Тег &#60;HEAD&#62;.
	 */
	HEAD("head"),

	/**
	 * Тег &#60;BODY&#62;.
	 */
	BODY("body"),

	/**
	 * Тег &#60;DIV&#62;.
	 */
	DIV("div"),

	/**
	 * Тег &#60;ASIDE&#62;.
	 */
	ASIDE("aside"),

	/**
	 * Тег &#60;FOOTER&#62;.
	 */
	FOOTER("footer"),

	/**
	 * Тег &#60;FORM&#62;.
	 */
	FORM("form"),

	/**
	 * Тег &#60;FRAME&#62;.
	 */
	FRAME("frame"),

	/**
	 * Тег &#60;HEADER&#62;.
	 */
	HEADER("header"),

	/**
	 * Тег &#60;IFRAME&#62;.
	 */
	IFRAME("iframe"),

	/**
	 * Тег &#60;MENU&#62;.
	 */
	MENU("menu"),

	/**
	 * Тег &#60;META&#62;.
	 */
	META("meta"),

	/**
	 * Тег &#60;SCRIPT&#62;.
	 */
	SCRIPT("script"),

	/**
	 * Тег &#60;SECTION&#62;.
	 */
	SECTION("section"),

	/**
	 * Тег &#60;TABLE&#62;.
	 */
	TABLE("table"),

	/**
	 * Тег &#60;TBODY&#62;.
	 */
	TBODY("tbody"),

	/**
	 * Тег &#60;A&#62;.
	 */
	A("a"),

	/**
	 * Тег &#60;ABBR&#62;.
	 */
	ABBR("abbr"),

	/**
	 * Тег &#60;ACRONYM&#62;.
	 */
	ACRONYM("acronym"),

	/**
	 * Тег &#60;ADDRESS&#62;.
	 */
	ADDRESS("address"),

	/**
	 * Тег &#60;APPLET&#62;.
	 */
	APPLET("applet"),

	/**
	 * Тег &#60;AREA&#62;.
	 */
	AREA("area"),

	/**
	 * Тег &#60;ARTICLE&#62;.
	 */
	ARTICLE("article"),

	/**
	 * Тег &#60;AUDIO&#62;.
	 */
	AUDIO("audio"),

	/**
	 * Тег &#60;B&#62;.
	 */
	B("b"),

	/**
	 * Тег &#60;BASE&#62;.
	 */
	BASE("base"),

	/**
	 * Тег &#60;BDI&#62;.
	 */
	BDI("bdi"),

	/**
	 * Тег &#60;BASEFONT&#62;.
	 */
	BASEFONT("basefont"),

	/**
	 * Тег &#60;BDO&#62;.
	 */
	BDO("bdo"),

	/**
	 * Тег &#60;BGSOUND&#62;.
	 */
	BGSOUND("bgsound"),

	/**
	 * Тег &#60;BIG&#62;.
	 */
	BIG("big"),

	/**
	 * Тег &#60;BLINK&#62;.
	 */
	BLINK("blink"),

	/**
	 * Тег &#60;BLOCKQUOTE&#62;.
	 */
	BLOCKQUOTE("blockquote"),

	/**
	 * Тег &#60;BR&#62;.
	 */
	BR("br"),

	/**
	 * Тег &#60;BUTTON&#62;.
	 */
	BUTTON("button"),

	/**
	 * Тег &#60;CANVAS&#62;.
	 */
	CANVAS("canvas"),

	/**
	 * Тег &#60;CAPTION&#62;.
	 */
	CAPTION("caption"),

	/**
	 * Тег &#60;CENTER&#62;.
	 */
	CENTER("center"),

	/**
	 * Тег &#60;CITE&#62;.
	 */
	CITE("cite"),

	/**
	 * Тег &#60;CODE&#62;.
	 */
	CODE("code"),

	/**
	 * Тег &#60;COMMENT&#62;.
	 */
	COMMENT("comment"),

	/**
	 * Тег &#60;COL&#62;.
	 */
	COL("col"),

	/**
	 * Тег &#60;COLGROUP&#62;.
	 */
	COLGROUP("colgroup"),

	/**
	 * Тег &#60;COMMAND&#62;.
	 */
	COMMAND("command"),

	/**
	 * Тег &#60;DATALIST&#62;.
	 */
	DATALIST("datalist"),

	/**
	 * Тег &#60;DD&#62;.
	 */
	DD("dd"),

	/**
	 * Тег &#60;DEL&#62;.
	 */
	DEL("del"),

	/**
	 * Тег &#60;DIR&#62;.
	 */
	DIR("dir"),

	/**
	 * Тег &#60;DETAILS&#62;.
	 */
	DETAILS("details"),

	/**
	 * Тег &#60;DFN&#62;.
	 */
	DFN("dfn"),

	/**
	 * Тег &#60;DL&#62;.
	 */
	DL("dl"),

	/**
	 * Тег &#60;DT&#62;.
	 */
	DT("dt"),

	/**
	 * Тег &#60;EM&#62;.
	 */
	EM("em"),

	/**
	 * Тег &#60;EMBED&#62;.
	 */
	EMBED("embed"),

	/**
	 * Тег &#60;FIELDSET&#62;.
	 */
	FIELDSET("fieldset"),

	/**
	 * Тег &#60;FIGCAPTION&#62;.
	 */
	FIGCAPTION("figcaption"),

	/**
	 * Тег &#60;FIGURE&#62;.
	 */
	FIGURE("figure"),

	/**
	 * Тег &#60;FONT&#62;.
	 */
	FONT("font"),

	/**
	 * Тег &#60;FRAMESET&#62;.
	 */
	FRAMESET("frameset"),

	/**
	 * Тег &#60;H1&#62;.
	 */
	H1("h1"),

	/**
	 * Тег &#60;H2&#62;.
	 */
	H2("h2"),

	/**
	 * Тег &#60;H3&#62;.
	 */
	H3("h3"),

	/**
	 * Тег &#60;H4&#62;.
	 */
	H4("h4"),

	/**
	 * Тег &#60;H5&#62;.
	 */
	H5("h5"),

	/**
	 * Тег &#60;H6&#62;.
	 */
	H6("h6"),

	/**
	 * Тег &#60;HGROUP&#62;.
	 */
	HGROUP("hgroup"),

	/**
	 * Тег &#60;HR&#62;.
	 */
	HR("hr"),

	/**
	 * Тег &#60;I&#62;.
	 */
	I("i"),

	/**
	 * Тег &#60;ISINDEX&#62;.
	 */
	ISINDEX("isindex"),

	/**
	 * Тег &#60;IMG&#62;.
	 */
	IMG("img"),

	/**
	 * Тег &#60;INPUT&#62;.
	 */
	INPUT("input"),

	/**
	 * Тег &#60;INS&#62;.
	 */
	INS("ins"),

	/**
	 * Тег &#60;KBD&#62;.
	 */
	KBD("kbd"),

	/**
	 * Тег &#60;KEYGEN&#62;.
	 */
	KEYGEN("keygen"),

	/**
	 * Тег &#60;LABEL&#62;.
	 */
	LABEL("label"),

	/**
	 * Тег &#60;LEGEND&#62;.
	 */
	LEGEND("legend"),

	/**
	 * Тег &#60;LI&#62;.
	 */
	LI("li"),

	/**
	 * Тег &#60;LINK&#62;.
	 */
	LINK("link"),

	/**
	 * Тег &#60;LISTING&#62;.
	 */
	LISTING("listing"),

	/**
	 * Тег &#60;MAIN&#62;.
	 */
	MAIN("main"),

	/**
	 * Тег &#60;MAP&#62;.
	 */
	MAP("map"),

	/**
	 * Тег &#60;MARK&#62;.
	 */
	MARK("mark"),

	/**
	 * Тег &#60;MARQUEE&#62;.
	 */
	MARQUEE("marquee"),

	/**
	 * Тег &#60;MATH&#62;.
	 */
	MATH("math"),

	/**
	 * Тег &#60;METER&#62;.
	 */
	METER("meter"),

	/**
	 * Тег &#60;NAV&#62;.
	 */
	NAV("nav"),

	/**
	 * Тег &#60;NEXTID&#62;.
	 */
	NEXTID("nextid"),

	/**
	 * Тег &#60;NOBR&#62;.
	 */
	NOBR("nobr"),

	/**
	 * Тег &#60;NOEMBED&#62;.
	 */
	NOEMBED("noembed"),

	/**
	 * Тег &#60;NOFRAMES&#62;.
	 */
	NOFRAMES("noframes"),

	/**
	 * Тег &#60;NOSCRIPT&#62;.
	 */
	NOSCRIPT("noscript"),

	/**
	 * Тег &#60;OBJECT&#62;.
	 */
	OBJECT("object"),

	/**
	 * Тег &#60;OL&#62;.
	 */
	OL("ol"),

	/**
	 * Тег &#60;OPTGROUP&#62;.
	 */
	OPTGROUP("optgroup"),

	/**
	 * Тег &#60;OPTION&#62;.
	 */
	OPTION("option"),

	/**
	 * Тег &#60;OUTPUT&#62;.
	 */
	OUTPUT("output"),

	/**
	 * Тег &#60;P&#62;.
	 */
	P("p"),

	/**
	 * Тег &#60;PARAM&#62;.
	 */
	PARAM("param"),

	/**
	 * Тег &#60;PLAINTEXT&#62;.
	 */
	PLAINTEXT("plaintext"),

	/**
	 * Тег &#60;PRE&#62;.
	 */
	PRE("pre"),

	/**
	 * Тег &#60;PROGRESS&#62;.
	 */
	PROGRESS("progress"),

	/**
	 * Тег &#60;Q&#62;.
	 */
	Q("q"),

	/**
	 * Тег &#60;RP&#62;.
	 */
	RP("rp"),

	/**
	 * Тег &#60;RT&#62;.
	 */
	RT("rt"),

	/**
	 * Тег &#60;RUBY&#62;.
	 */
	RUBY("ruby"),

	/**
	 * Тег &#60;S&#62;.
	 */
	S("s"),

	/**
	 * Тег &#60;SMALL&#62;.
	 */
	SMALL("small"),

	/**
	 * Тег &#60;SAMP&#62;.
	 */
	SAMP("samp"),

	/**
	 * Тег &#60;SELECT&#62;.
	 */
	SELECT("select"),

	/**
	 * Тег &#60;SOURCE&#62;.
	 */
	SOURCE("source"),

	/**
	 * Тег &#60;SPACER&#62;.
	 */
	SPACER("spacer"),

	/**
	 * Тег &#60;SPAN&#62;.
	 */
	SPAN("span"),

	/**
	 * Тег &#60;STRIKE&#62;.
	 */
	STRIKE("strike"),

	/**
	 * Тег &#60;STRONG&#62;.
	 */
	STRONG("strong"),

	/**
	 * Тег &#60;STYLE&#62;.
	 */
	STYLE("style"),

	/**
	 * Тег &#60;SUB&#62;.
	 */
	SUB("sub"),

	/**
	 * Тег &#60;SUMMARY&#62;.
	 */
	SUMMARY("summary"),

	/**
	 * Тег &#60;SUP&#62;.
	 */
	SUP("sup"),

	/**
	 * Тег &#60;SVG&#62;.
	 */
	SVG("svg"),

	/**
	 * Тег &#60;TD&#62;.
	 */
	TD("td"),

	/**
	 * Тег &#60;TEXTAREA&#62;.
	 */
	TEXTAREA("textarea"),

	/**
	 * Тег &#60;TFOOT&#62;.
	 */
	TFOOT("tfoot"),

	/**
	 * Тег &#60;TH&#62;.
	 */
	TH("th"),

	/**
	 * Тег &#60;THEAD&#62;.
	 */
	THEAD("thead"),

	/**
	 * Тег &#60;TIME&#62;.
	 */
	TIME("time"),

	/**
	 * Тег &#60;TITLE&#62;.
	 */
	TITLE("title"),

	/**
	 * Тег &#60;TR&#62;.
	 */
	TR("tr"),

	/**
	 * Тег &#60;TRACK&#62;.
	 */
	TRACK("track"),

	/**
	 * Тег &#60;TT&#62;.
	 */
	TT("tt"),

	/**
	 * Тег &#60;U&#62;.
	 */
	U("u"),

	/**
	 * Тег &#60;UL&#62;.
	 */
	UL("ul"),

	/**
	 * Тег &#60;VAR&#62;.
	 */
	VAR("var"),

	/**
	 * Тег &#60;VIDEO&#62;.
	 */
	VIDEO("video"),

	/**
	 * Тег &#60;WBR&#62;.
	 */
	WBR("wbr"),

	/**
	 * Тег &#60;XMP&#62;.
	 */
	XMP("xmp");

	/**
	 * Название HTML-тег.
	 */
	@NotNull
	private final String name;

	/**
	 * Конструктор для {@code Tag}.
	 *
	 * @param name название HTML-тега.
	 */
	Tag(@NotNull final String name)
	{
		this.name = name;
	}

	/**
	 * Возвращает название HTML-тега.
	 *
	 * @return название HTML-тега.
	 */
	@NotNull
	public String getName()
	{
		return this.name;
	}
}
