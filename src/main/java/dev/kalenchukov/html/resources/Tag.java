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

package dev.kalenchukov.html.resources;

import org.jetbrains.annotations.NotNull;

/**
 * Перечисление HTML-тегов.
 */
public enum Tag
{
	HTML("html"),
	HEAD("head"),
	BODY("body"),
	DIV("div"),
	ASIDE("aside"),
	FOOTER("footer"),
	FORM("form"),
	FRAME("frame"),
	HEADER("header"),
	IFRAME("iframe"),
	MENU("menu"),
	META("meta"),
	SCRIPT("script"),
	SECTION("section"),
	TABLE("table"),
	TBODY("tbody"),
	A("a"),
	ABBR("abbr"),
	ACRONYM("acronym"),
	ADDRESS("address"),
	APPLET("applet"),
	AREA("area"),
	ARTICLE("article"),
	AUDIO("audio"),
	B("b"),
	BASE("base"),
	BDI("bdi"),
	BASEFONT("basefont"),
	BDO("bdo"),
	BGSOUND("bgsound"),
	BIG("big"),
	BLINK("blink"),
	BLOCKQUOTE("blockquote"),
	BR("br"),
	BUTTON("button"),
	CANVAS("canvas"),
	CAPTION("caption"),
	CENTER("center"),
	CITE("cite"),
	CODE("code"),
	COMMENT("comment"),
	COL("col"),
	COLGROUP("colgroup"),
	COMMAND("command"),
	DATALIST("datalist"),
	DD("dd"),
	DEL("del"),
	DIR("dir"),
	DETAILS("details"),
	DFN("dfn"),
	DL("dl"),
	DT("dt"),
	EM("em"),
	EMBED("embed"),
	FIELDSET("fieldset"),
	FIGCAPTION("figcaption"),
	FIGURE("figure"),
	FONT("font"),
	FRAMESET("frameset"),
	H1("h1"),
	H2("h2"),
	H3("h3"),
	H4("h4"),
	H5("h5"),
	H6("h6"),
	HGROUP("hgroup"),
	HR("hr"),
	I("i"),
	ISINDEX("isindex"),
	IMG("img"),
	INPUT("input"),
	INS("ins"),
	KBD("kbd"),
	KEYGEN("keygen"),
	LABEL("label"),
	LEGEND("legend"),
	LI("li"),
	LINK("link"),
	LISTING("listing"),
	MAIN("main"),
	MAP("map"),
	MARK("mark"),
	MARQUEE("marquee"),
	MATH("math"),
	METER("meter"),
	NAV("nav"),
	NEXTID("nextid"),
	NOBR("nobr"),
	NOEMBED("noembed"),
	NOFRAMES("noframes"),
	NOSCRIPT("noscript"),
	OBJECT("object"),
	OL("ol"),
	OPTGROUP("optgroup"),
	OPTION("option"),
	OUTPUT("output"),
	P("p"),
	PARAM("param"),
	PLAINTEXT("plaintext"),
	PRE("pre"),
	PROGRESS("progress"),
	Q("q"),
	RP("rp"),
	RT("rt"),
	RUBY("ruby"),
	S("s"),
	SMALL("small"),
	SAMP("samp"),
	SELECT("select"),
	SOURCE("source"),
	SPACER("spacer"),
	SPAN("span"),
	STRIKE("strike"),
	STRONG("strong"),
	STYLE("style"),
	SUB("sub"),
	SUMMARY("summary"),
	SUP("sup"),
	SVG("svg"),
	TD("td"),
	TEXTAREA("textarea"),
	TFOOT("tfoot"),
	TH("th"),
	THEAD("thead"),
	TIME("time"),
	TITLE("title"),
	TR("tr"),
	TRACK("track"),
	TT("tt"),
	U("u"),
	UL("ul"),
	VAR("var"),
	VIDEO("video"),
	WBR("wbr"),
	XMP("xmp");

	/**
	 * Название HTML-тег.
	 */
	@NotNull
	private final String name;

	/**
	 * Конструктор для {@code Tag}.
	 *
	 * @param name Название HTML-тега.
	 */
	Tag(@NotNull final String name)
	{
		this.name = name;
	}

	/**
	 * Возвращает название HTML-тега.
	 *
	 * @return Название HTML-тега.
	 */
	@NotNull
	public String getName()
	{
		return this.name;
	}
}
