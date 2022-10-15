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
 * Перечисление HTML-сущностей.
 */
public enum Entity
{
	AACUTE('Á', "Aacute", "193", "C1"),
	AACUTE2('á', "aacute", "225", "E1"),
	ABREVE('Ă', "Abreve", "258", "102"),
	ABREVE2('ă', "abreve", "259", "103"),
	AC('∾', "ac", "8766", "223E"),
	ACD('∿', "acd", "8767", "223F"),
	ACIRC('Â', "Acirc", "194", "C2"),
	ACIRC2('â', "acirc", "226", "E2"),
	ACUTE('´', "acute", "180", "B4"),
	AELIG('Æ', "AElig", "198", "C6"),
	AELIG2('æ', "aelig", "230", "E6"),
	AGRAVE('À', "Agrave", "192", "C0"),
	AGRAVE2('à', "agrave", "224", "E0"),
	ALEFSYM('ℵ', "alefsym", "8501", "2135"),
	ALPHA('Α', "Alpha", "913", "391"),
	ALPHA2('α', "alpha", "945", "3B1"),
	AMACR('Ā', "Amacr", "256", "100"),
	AMACR2('ā', "amacr", "257", "101"),
	AMP('&', "amp", "38", "26"),
	AND('∧', "and", "8743", "2227"),
	ANG('∠', "ang", "8736", "2220"),
	ANGMSD('∡', "angmsd", "8737", "2221"),
	ANGRT('∟', "angrt", "8735", "221F"),
	ANGRTVB('⊾', "angrtvb", "8894", "22BE"),
	ANGSPH('∢', "angsph", "8738", "2222"),
	AOGON('Ą', "Aogon", "260", "104"),
	AOGON2('ą', "aogon", "261", "105"),
	APID('≋', "apid", "8779", "224B"),
	APOS('\'', "apos", "39", "27"),
	APPROXEQ('≊', "approxeq", "8778", "224A"),
	ARING('Å', "Aring", "197", "C5"),
	ARING2('å', "aring", "229", "E5"),
	AST('*', "ast", "42", "2A"),
	ASYMP('≈', "asymp", "8776", "2248"),
	ASYMPEQ('≍', "asympeq", "8781", "224D"),
	ATILDE('Ã', "Atilde", "195", "C3"),
	ATILDE2('ã', "atilde", "227", "E3"),
	AUML('Ä', "Auml", "196", "C4"),
	AUML2('ä', "auml", "228", "E4"),
	AWCONINT('∳', "awconint", "8755", "2233"),
	BARVEE('⊽', "barvee", "8893", "22BD"),
	BCONG('≌', "bcong", "8780", "224C"),
	BDQUO('„', "bdquo", "8222", "201E"),
	BECAUSE('∵', "because", "8757", "2235"),
	BERNOU('ℬ', "bernou", "8492", "212C"),
	BETA('Β', "Beta", "914", "392"),
	BETA2('β', "beta", "946", "3B2"),
	BETH('ℶ', "beth", "8502", "2136"),
	BETWEEN('≬', "between", "8812", "226C"),
	BOWTIE('⋈', "bowtie", "8904", "22C8"),
	BPRIME('‵', "bprime", "8245", "2035"),
	BRVBAR('¦', "brvbar", "166", "A6"),
	BSEMI('⁏', "bsemi", "8271", "204F"),
	BSIM('∽', "bsim", "8765", "223D"),
	BSIME('⋍', "bsime", "8909", "22CD"),
	BSOL('\\', "bsol", "92", "5C"),
	BULL('•', "bull", "8226", "2022"),
	BUMP('≎', "bump", "8782", "224E"),
	BUMPE('≏', "bumpe", "8783", "224F"),
	CACUTE('Ć', "Cacute", "262", "106"),
	CACUTE2('ć', "cacute", "263", "107"),
	CAP('∩', "cap", "8745", "2229"),
	CAP2('⋒', "Cap", "8914", "22D2"),
	CARET('⁁', "caret", "8257", "2041"),
	CCARON('Č', "Ccaron", "268", "10C"),
	CCARON2('č', "ccaron", "269", "10D"),
	CCEDIL('Ç', "Ccedil", "199", "C7"),
	CCEDIL2('ç', "ccedil", "231", "E7"),
	CCIRC('Ĉ', "Ccirc", "264", "108"),
	CCIRC2('ĉ', "ccirc", "265", "109"),
	CCONINT('∰', "Cconint", "8752", "2230"),
	CDOT('Ċ', "Cdot", "266", "10A"),
	CDOT2('ċ', "cdot", "267", "10B"),
	CEDIL('¸', "cedil", "184", "B8"),
	CENT('¢', "cent", "162", "A2"),
	CFR('ℭ', "Cfr", "8493", "212D"),
	CHECK('✓', "check", "10003", "2713"),
	CHI('Χ', "Chi", "935", "3A7"),
	CHI2('χ', "chi", "967", "3C7"),
	CIRC('ˆ', "circ", "710", "2C6"),
	CIRE('≗', "cire", "8791", "2257"),
	CLUBS('♣', "clubs", "9827", "2663"),
	COLON(':', "colon", "58", "3A"),
	COLON2('∷', "Colon", "8759", "2237"),
	COLONE('≔', "colone", "8788", "2254"),
	COMMA(',', "comma", "44", "2C"),
	COMMAT('@', "commat", "64", "40"),
	COMP('∁', "comp", "8705", "2201"),
	COMPFN('∘', "compfn", "8728", "2218"),
	CONG('≅', "cong", "8773", "2245"),
	CONINT('∮', "conint", "8750", "222E"),
	CONINT2('∯', "Conint", "8751", "222F"),
	COPF('ℂ', "Copf", "8450", "2102"),
	COPROD('∐', "coprod", "8720", "2210"),
	COPY('©', "copy", "169", "A9"),
	COPYSR('℗', "copysr", "8471", "2117"),
	CROSS('✗', "cross", "10007", "2717"),
	CTDOT('⋯', "ctdot", "8943", "22EF"),
	CUDARRL('⤸', "cudarrl", "10552", "2938"),
	CUDARRR('⤵', "cudarrr", "10549", "2935"),
	CUEPR('⋞', "cuepr", "8926", "22DE"),
	CUESC('⋟', "cuesc", "8927", "22DF"),
	CULARRP('⤽', "cularrp", "10557", "293D"),
	CUP('∪', "cup", "8746", "222A"),
	CUP2('⋓', "Cup", "8915", "22D3"),
	CUPDOT('⊍', "cupdot", "8845", "228D"),
	CURARRM('⤼', "curarrm", "10556", "293C"),
	CURREN('¤', "curren", "164", "A4"),
	CUVEE('⋎', "cuvee", "8910", "22CE"),
	CUWED('⋏', "cuwed", "8911", "22CF"),
	CWCONINT('∲', "cwconint", "8754", "2232"),
	CWINT('∱', "cwint", "8753", "2231"),
	DAGGER('†', "dagger", "8224", "2020"),
	DAGGER2('‡', "Dagger", "8225", "2021"),
	DALETH('ℸ', "daleth", "8504", "2138"),
	DARR('⇓', "dArr", "8659", "21D3"),
	DASHV('⊣', "dashv", "8867", "22A3"),
	DCARON('Ď', "Dcaron", "270", "10E"),
	DCARON2('ď', "dcaron", "271", "10F"),
	DD('ⅅ', "DD", "8517", "2145"),
	DD2('ⅆ', "dd", "8518", "2146"),
	DDARR('⇊', "ddarr", "8650", "21CA"),
	DDOTRAHD('⤑', "DDotrahd", "10513", "2911"),
	DEG('°', "deg", "176", "B0"),
	DELTA('Δ', "Delta", "916", "394"),
	DELTA2('δ', "delta", "948", "3B4"),
	DFISHT('⥿', "dfisht", "10623", "297F"),
	DHAR('⥥', "dHar", "10597", "2965"),
	DHARL('⇃', "dharl", "8643", "21C3"),
	DHARR('⇂', "dharr", "8642", "21C2"),
	DIAMOND('⋄', "diamond", "8900", "22C4"),
	DIAMS('♦', "diams", "9830", "2666"),
	DISIN('⋲', "disin", "8946", "22F2"),
	DIVIDE('÷', "divide", "247", "F7"),
	DIVONX('⋇', "divonx", "8903", "22C7"),
	DOLLAR('$', "dollar", "36", "24"),
	DOWNARROWBAR('⤓', "DownArrowBar", "10515", "2913"),
	DOWNBREVE('̑', "DownBreve", "785", "311"),
	DOWNLEFTRIGHTVECTOR('⥐', "DownLeftRightVector", "10576", "2950"),
	DOWNLEFTTEEVECTOR('⥞', "DownLeftTeeVector", "10590", "295E"),
	DOWNLEFTVECTORBAR('⥖', "DownLeftVectorBar", "10582", "2956"),
	DOWNRIGHTTEEVECTOR('⥟', "DownRightTeeVector", "10591", "295F"),
	DOWNRIGHTVECTORBAR('⥗', "DownRightVectorBar", "10583", "2957"),
	DSTROK('Đ', "Dstrok", "272", "110"),
	DSTROK2('đ', "dstrok", "273", "111"),
	DTDOT('⋱', "dtdot", "8945", "22F1"),
	DUARR('⇵', "duarr", "8693", "21F5"),
	DUHAR('⥯', "duhar", "10607", "296F"),
	DZIGRARR('⟿', "dzigrarr", "10239", "27FF"),
	EACUTE('É', "Eacute", "201", "C9"),
	EACUTE2('é', "eacute", "233", "E9"),
	ECARON('Ě', "Ecaron", "282", "11A"),
	ECARON2('ě', "ecaron", "283", "11B"),
	ECIR('≖', "ecir", "8790", "2256"),
	ECIRC('Ê', "Ecirc", "202", "CA"),
	ECIRC2('ê', "ecirc", "234", "EA"),
	ECOLON('≕', "ecolon", "8789", "2255"),
	EDOT('Ė', "Edot", "278", "116"),
	EDOT2('ė', "edot", "279", "117"),
	EDOT3('≑', "eDot", "8785", "2251"),
	EE('ⅇ', "ee", "8519", "2147"),
	EFDOT('≒', "efDot", "8786", "2252"),
	EGRAVE('È', "Egrave", "200", "C8"),
	EGRAVE2('è', "egrave", "232", "E8"),
	ELL('ℓ', "ell", "8467", "2113"),
	EMACR('Ē', "Emacr", "274", "112"),
	EMACR2('ē', "emacr", "275", "113"),
	EMPTY('∅', "empty", "8709", "2205"),
	EMSP(' ', "emsp", "8195", "2003"),
	ENG('Ŋ', "ENG", "330", "14A"),
	ENG2('ŋ', "eng", "331", "14B"),
	ENSP(' ', "ensp", "8194", "2002"),
	EOGON('Ę', "Eogon", "280", "118"),
	EOGON2('ę', "eogon", "281", "119"),
	EPAR('⋕', "epar", "8917", "22D5"),
	EPSILON('Ε', "Epsilon", "917", "395"),
	EPSILON2('ε', "epsilon", "949", "3B5"),
	EQUALS('=', "equals", "61", "3D"),
	EQUEST('≟', "equest", "8799", "225F"),
	EQUIV('≡', "equiv", "8801", "2261"),
	ERARR('⥱', "erarr", "10609", "2971"),
	ERDOT('≓', "erDot", "8787", "2253"),
	ESCR('ℯ', "escr", "8495", "212F"),
	ESCR2('ℰ', "Escr", "8496", "2130"),
	ESDOT('≐', "esdot", "8784", "2250"),
	ESIM('≂', "esim", "8770", "2242"),
	ETA('Η', "Eta", "919", "397"),
	ETA2('η', "eta", "951", "3B7"),
	ETH('Ð', "ETH", "208", "D0"),
	ETH2('ð', "eth", "240", "F0"),
	EUML('Ë', "Euml", "203", "CB"),
	EUML2('ë', "euml", "235", "EB"),
	EXCL('!', "excl", "33", "21"),
	EXIST('∃', "exist", "8707", "2203"),
	FEMALE('♀', "female", "9792", "2640"),
	FLAT('♭', "flat", "9837", "266D"),
	FNOF('ƒ', "fnof", "402", "192"),
	FORALL('∀', "forall", "8704", "2200"),
	FORK('⋔', "fork", "8916", "22D4"),
	FRAC12('½', "frac12", "189", "BD"),
	FRAC13('⅓', "frac13", "8531", "2153"),
	FRAC14('¼', "frac14", "188", "BC"),
	FRAC15('⅕', "frac15", "8533", "2155"),
	FRAC16('⅙', "frac16", "8537", "2159"),
	FRAC18('⅛', "frac18", "8539", "215B"),
	FRAC23('⅔', "frac23", "8532", "2154"),
	FRAC25('⅖', "frac25", "8534", "2156"),
	FRAC34('¾', "frac34", "190", "BE"),
	FRAC35('⅗', "frac35", "8535", "2157"),
	FRAC38('⅜', "frac38", "8540", "215C"),
	FRAC45('⅘', "frac45", "8536", "2158"),
	FRAC56('⅚', "frac56", "8538", "215A"),
	FRAC58('⅝', "frac58", "8541", "215D"),
	FRAC78('⅞', "frac78", "8542", "215E"),
	FRASL('⁄', "frasl", "8260", "2044"),
	FSCR('ℱ', "Fscr", "8497", "2131"),
	GAMMA('Γ', "Gamma", "915", "393"),
	GAMMA2('γ', "gamma", "947", "3B3"),
	GBREVE('Ğ', "Gbreve", "286", "11E"),
	GBREVE2('ğ', "gbreve", "287", "11F"),
	GCEDIL('Ģ', "Gcedil", "290", "122"),
	GCIRC('Ĝ', "Gcirc", "284", "11C"),
	GCIRC2('ĝ', "gcirc", "285", "11D"),
	GDOT('Ġ', "Gdot", "288", "120"),
	GDOT2('ġ', "gdot", "289", "121"),
	GE('≥', "ge", "8805", "2265"),
	GE2('≧', "gE", "8807", "2267"),
	GEL('⋛', "gel", "8923", "22DB"),
	GG('⋙', "Gg", "8921", "22D9"),
	GIMEL('ℷ', "gimel", "8503", "2137"),
	GL('≷', "gl", "8823", "2277"),
	GNE('≩', "gnE", "8809", "2269"),
	GNSIM('⋧', "gnsim", "8935", "22E7"),
	GRAVE('`', "grave", "96", "60"),
	GSCR('ℊ', "gscr", "8458", "210A"),
	GSIM('≳', "gsim", "8819", "2273"),
	GT('>', "gt", "62", "3E"),
	GT2('≫', "Gt", "8811", "226B"),
	GTDOT('⋗', "gtdot", "8919", "22D7"),
	GTRARR('⥸', "gtrarr", "10616", "2978"),
	HAMILT('ℋ', "hamilt", "8459", "210B"),
	HARR('⇔', "hArr", "8660", "21D4"),
	HARRCIR('⥈', "harrcir", "10568", "2948"),
	HAT('^', "Hat", "94", "5E"),
	HCIRC('Ĥ', "Hcirc", "292", "124"),
	HCIRC2('ĥ', "hcirc", "293", "125"),
	HEARTS('♥', "hearts", "9829", "2665"),
	HELLIP('…', "hellip", "8230", "2026"),
	HERCON('⊹', "hercon", "8889", "22B9"),
	HFR('ℌ', "Hfr", "8460", "210C"),
	HOARR('⇿', "hoarr", "8703", "21FF"),
	HOMTHT('∻', "homtht", "8763", "223B"),
	HOPF('ℍ', "Hopf", "8461", "210D"),
	HORBAR('―', "horbar", "8213", "2015"),
	HSTROK('Ħ', "Hstrok", "294", "126"),
	HSTROK2('ħ', "hstrok", "295", "127"),
	HYBULL('⁃', "hybull", "8259", "2043"),
	HYPHEN('‐', "hyphen", "8208", "2010"),
	IACUTE('í', "iacute", "237", "ED"),
	ICIRC('î', "icirc", "238", "EE"),
	IDOT('İ', "Idot", "304", "130"),
	IEXCL('¡', "iexcl", "161", "A1"),
	IGRAVE('ì', "igrave", "236", "EC"),
	II('ⅈ', "ii", "8520", "2148"),
	IIINT('∭', "iiint", "8749", "222D"),
	IIOTA('℩', "iiota", "8489", "2129"),
	IJLIG('Ĳ', "IJlig", "306", "132"),
	IJLIG2('ĳ', "ijlig", "307", "133"),
	IMACR('Ī', "Imacr", "298", "12A"),
	IMACR2('ī', "imacr", "299", "12B"),
	IMAGE('ℑ', "image", "8465", "2111"),
	IMATH('ı', "imath", "305", "131"),
	IMOF('⊷', "imof", "8887", "22B7"),
	INCARE('℅', "incare", "8453", "2105"),
	INFIN('∞', "infin", "8734", "221E"),
	INT('∫', "int", "8747", "222B"),
	INT2('∬', "Int", "8748", "222C"),
	INTCAL('⊺', "intcal", "8890", "22BA"),
	IOGON('Į', "Iogon", "302", "12E"),
	IOGON2('į', "iogon", "303", "12F"),
	IOTA('Ι', "Iota", "921", "399"),
	IOTA2('ι', "iota", "953", "3B9"),
	IQUEST('¿', "iquest", "191", "BF"),
	ISCR('ℐ', "Iscr", "8464", "2110"),
	ISIN('∈', "isin", "8712", "2208"),
	ISINDOT('⋵', "isindot", "8949", "22F5"),
	ISINE('⋹', "isinE", "8953", "22F9"),
	ISINS('⋴', "isins", "8948", "22F4"),
	ISINSV('⋳', "isinsv", "8947", "22F3"),
	ITILDE('Ĩ', "Itilde", "296", "128"),
	ITILDE2('ĩ', "itilde", "297", "129"),
	IUML('ï', "iuml", "239", "EF"),
	JCIRC('Ĵ', "Jcirc", "308", "134"),
	JCIRC2('ĵ', "jcirc", "309", "135"),
	KAPPA('Κ', "Kappa", "922", "39A"),
	KAPPA2('κ', "kappa", "954", "3BA"),
	KCEDIL('Ķ', "Kcedil", "310", "136"),
	KCEDIL2('ķ', "kcedil", "311", "137"),
	KGREEN('ĸ', "kgreen", "312", "138"),
	LAARR('⇚', "lAarr", "8666", "21DA"),
	LACUTE('Ĺ', "Lacute", "313", "139"),
	LACUTE2('ĺ', "lacute", "314", "13A"),
	LAMBDA('Λ', "Lambda", "923", "39B"),
	LAMBDA2('λ', "lambda", "955", "3BB"),
	LANG('〈', "lang", "9001", "2329"),
	LAQUO('«', "laquo", "171", "AB"),
	LARR('⇐', "lArr", "8656", "21D0"),
	LARRB('⇤', "larrb", "8676", "21E4"),
	LARRBFS('⤟', "larrbfs", "10527", "291F"),
	LARRFS('⤝', "larrfs", "10525", "291D"),
	LARRPL('⤹', "larrpl", "10553", "2939"),
	LARRSIM('⥳', "larrsim", "10611", "2973"),
	LATAIL('⤙', "latail", "10521", "2919"),
	LATAIL2('⤛', "lAtail", "10523", "291B"),
	LBARR('⤌', "lbarr", "10508", "290C"),
	LBARR2('⤎', "lBarr", "10510", "290E"),
	LBBRK('❲', "lbbrk", "10098", "2772"),
	LBRACE('{', "lbrace", "123", "7B"),
	LBRACK('[', "lbrack", "91", "5B"),
	LCARON('Ľ', "Lcaron", "317", "13D"),
	LCARON2('ľ', "lcaron", "318", "13E"),
	LCEDIL('Ļ', "Lcedil", "315", "13B"),
	LCEDIL2('ļ', "lcedil", "316", "13C"),
	LCEIL('⌈', "lceil", "8968", "2308"),
	LCIRC('Î', "Lcirc", "206", "CE"),
	LDCA('⤶', "ldca", "10550", "2936"),
	LDQUO('“', "ldquo", "8220", "201C"),
	LDRDHAR('⥧', "ldrdhar", "10599", "2967"),
	LDRUSHAR('⥋', "ldrushar", "10571", "294B"),
	LE('≤', "le", "8804", "2264"),
	LE2('≦', "lE", "8806", "2266"),
	LEFTDOWNTEEVECTOR('⥡', "LeftDownTeeVector", "10593", "2961"),
	LEFTDOWNVECTORBAR('⥙', "LeftDownVectorBar", "10585", "2959"),
	LEFTTEEVECTOR('⥚', "LeftTeeVector", "10586", "295A"),
	LEFTUPDOWNVECTOR('⥑', "LeftUpDownVector", "10577", "2951"),
	LEFTUPTEEVECTOR('⥠', "LeftUpTeeVector", "10592", "2960"),
	LEFTUPVECTORBAR('⥘', "LeftUpVectorBar", "10584", "2958"),
	LEFTVECTORBAR('⥒', "LeftVectorBar", "10578", "2952"),
	LEG('⋚', "leg", "8922", "22DA"),
	LFISHT('⥼', "lfisht", "10620", "297C"),
	LFLOOR('⌊', "lfloor", "8970", "230A"),
	LG('≶', "lg", "8822", "2276"),
	LGRAVE('Ì', "Lgrave", "204", "CC"),
	LHAR('⥢', "lHar", "10594", "2962"),
	LHARD('↽', "lhard", "8637", "21BD"),
	LHARU('↼', "lharu", "8636", "21BC"),
	LHARUL('⥪', "lharul", "10602", "296A"),
	LL('⋘', "Ll", "8920", "22D8"),
	LLARR('⇇', "llarr", "8647", "21C7"),
	LLHARD('⥫', "llhard", "10603", "296B"),
	LMIDOT('Ŀ', "Lmidot", "319", "13F"),
	LMIDOT2('ŀ', "lmidot", "320", "140"),
	LNE('≨', "lnE", "8808", "2268"),
	LNSIM('⋦', "lnsim", "8934", "22E6"),
	LOARR('⇽', "loarr", "8701", "21FD"),
	LOWAST('∗', "lowast", "8727", "2217"),
	LOWBAR('_', "lowbar", "95", "5F"),
	LOZ('◊', "loz", "9674", "25CA"),
	LPAR('(', "lpar", "40", "28"),
	LRARR('⇆', "lrarr", "8646", "21C6"),
	LRHAR('⇋', "lrhar", "8651", "21CB"),
	LRHARD('⥭', "lrhard", "10605", "296D"),
	LRM('‎', "lrm", "8206", "200E"),
	LRTRI('⊿', "lrtri", "8895", "22BF"),
	LSAQUO('‹', "lsaquo", "8249", "2039"),
	LSCR('ℒ', "Lscr", "8466", "2112"),
	LSIM('≲', "lsim", "8818", "2272"),
	LSQUO('‘', "lsquo", "8216", "2018"),
	LSTROK('Ł', "Lstrok", "321", "141"),
	LSTROK2('ł', "lstrok", "322", "142"),
	LT('<', "lt", "60", "3C"),
	LT2('≪', "Lt", "8810", "226A"),
	LTDOT('⋖', "ltdot", "8918", "22D6"),
	LTHREE('⋋', "lthree", "8907", "22CB"),
	LTIMES('⋉', "ltimes", "8905", "22C9"),
	LTLARR('⥶', "ltlarr", "10614", "2976"),
	LTRIE('⊴', "ltrie", "8884", "22B4"),
	LUML('Ï', "Luml", "207", "CF"),
	LURDSHAR('⥊', "lurdshar", "10570", "294A"),
	LURUHAR('⥦', "luruhar", "10598", "2966"),
	MACR('¯', "macr", "175", "AF"),
	MALE('♂', "male", "9794", "2642"),
	MALT('✠', "malt", "10016", "2720"),
	MAP('⤅', "Map", "10501", "2905"),
	MDASH('—', "mdash", "8212", "2014"),
	MDDOT('∺', "mDDot", "8762", "223A"),
	MHO('℧', "mho", "8487", "2127"),
	MICRO('µ', "micro", "181", "B5"),
	MID('∣', "mid", "8739", "2223"),
	MIDDOT('·', "middot", "183", "B7"),
	MINUS('−', "minus", "8722", "2212"),
	MINUSB('⊟', "minusb", "8863", "229F"),
	MINUSD('∸', "minusd", "8760", "2238"),
	MNPLUS('∓', "mnplus", "8723", "2213"),
	MODELS('⊧', "models", "8871", "22A7"),
	MSCR('ℳ', "Mscr", "8499", "2133"),
	MU('Μ', "Mu", "924", "39C"),
	MU2('μ', "mu", "956", "3BC"),
	MUMAP('⊸', "mumap", "8888", "22B8"),
	NABLA('∇', "nabla", "8711", "2207"),
	NACUTE('Ń', "Nacute", "323", "143"),
	NACUTE2('ń', "nacute", "324", "144"),
	NAP('≉', "nap", "8777", "2249"),
	NAPOS('ŉ', "napos", "329", "149"),
	NATURAL('♮', "natural", "9838", "266E"),
	NBSP(' ', "nbsp", "160", "A0"),
	NCARON('Ň', "Ncaron", "327", "147"),
	NCARON2('ň', "ncaron", "328", "148"),
	NCEDIL('Ņ', "Ncedil", "325", "145"),
	NCEDIL2('ņ', "ncedil", "326", "146"),
	NCONG('≇', "ncong", "8775", "2247"),
	NDASH('–', "ndash", "8211", "2013"),
	NE('≠', "ne", "8800", "2260"),
	NEARHK('⤤', "nearhk", "10532", "2924"),
	NEARR('⇗', "neArr", "8663", "21D7"),
	NEQUIV('≢', "nequiv", "8802", "2262"),
	NESEAR('⤨', "nesear", "10536", "2928"),
	NEXIST('∄', "nexist", "8708", "2204"),
	NGE('≱', "nge", "8817", "2271"),
	NGSIM('≵', "ngsim", "8821", "2275"),
	NGT('≯', "ngt", "8815", "226F"),
	NHARR('⇎', "nhArr", "8654", "21CE"),
	NI('∋', "ni", "8715", "220B"),
	NIS('⋼', "nis", "8956", "22FC"),
	NISD('⋺', "nisd", "8954", "22FA"),
	NLARR('⇍', "nlArr", "8653", "21CD"),
	NLDR('‥', "nldr", "8229", "2025"),
	NLE('≰', "nle", "8816", "2270"),
	NLSIM('≴', "nlsim", "8820", "2274"),
	NLT('≮', "nlt", "8814", "226E"),
	NLTRI('⋪', "nltri", "8938", "22EA"),
	NLTRIE('⋬', "nltrie", "8940", "22EC"),
	NMID('∤', "nmid", "8740", "2224"),
	NOPF('ℕ', "Nopf", "8469", "2115"),
	NOT('¬', "not", "172", "AC"),
	NOTCUPCAP('≭', "NotCupCap", "8813", "226D"),
	NOTIN('∉', "notin", "8713", "2209"),
	NOTINVB('⋷', "notinvb", "8951", "22F7"),
	NOTINVC('⋶', "notinvc", "8950", "22F6"),
	NOTNI('∌', "notni", "8716", "220C"),
	NOTNIVB('⋾', "notnivb", "8958", "22FE"),
	NOTNIVC('⋽', "notnivc", "8957", "22FD"),
	NPAR('∦', "npar", "8742", "2226"),
	NPR('⊀', "npr", "8832", "2280"),
	NPRCUE('⋠', "nprcue", "8928", "22E0"),
	NRARR('⇏', "nrArr", "8655", "21CF"),
	NRTRI('⋫', "nrtri", "8939", "22EB"),
	NRTRIE('⋭', "nrtrie", "8941", "22ED"),
	NSC('⊁', "nsc", "8833", "2281"),
	NSCCUE('⋡', "nsccue", "8929", "22E1"),
	NSIM('≁', "nsim", "8769", "2241"),
	NSIME('≄', "nsime", "8772", "2244"),
	NSQSUBE('⋢', "nsqsube", "8930", "22E2"),
	NSQSUPE('⋣', "nsqsupe", "8931", "22E3"),
	NSUB('⊄', "nsub", "8836", "2284"),
	NSUBE('⊈', "nsube", "8840", "2288"),
	NSUP('⊅', "nsup", "8837", "2285"),
	NSUPE('⊉', "nsupe", "8841", "2289"),
	NTGL('≹', "ntgl", "8825", "2279"),
	NTILDE('Ñ', "Ntilde", "209", "D1"),
	NTILDE2('ñ', "ntilde", "241", "F1"),
	NTLG('≸', "ntlg", "8824", "2278"),
	NU('Ν', "Nu", "925", "39D"),
	NU2('ν', "nu", "957", "3BD"),
	NUM('#', "num", "35", "23"),
	NUMERO('№', "numero", "8470", "2116"),
	NVDASH('⊬', "nvdash", "8876", "22AC"),
	NVDASH2('⊭', "nvDash", "8877", "22AD"),
	NVDASH3('⊮', "nVdash", "8878", "22AE"),
	NVDASH4('⊯', "nVDash", "8879", "22AF"),
	NVHARR('⤄', "nvHarr", "10500", "2904"),
	NVLARR('⤂', "nvlArr", "10498", "2902"),
	NVRARR('⤃', "nvrArr", "10499", "2903"),
	NWARHK('⤣', "nwarhk", "10531", "2923"),
	NWARR('⇖', "nwArr", "8662", "21D6"),
	NWNEAR('⤧', "nwnear", "10535", "2927"),
	OACUTE('Ó', "Oacute", "211", "D3"),
	OACUTE2('ó', "oacute", "243", "F3"),
	OAST('⊛', "oast", "8859", "229B"),
	OCIR('⊚', "ocir", "8858", "229A"),
	OCIRC('Ô', "Ocirc", "212", "D4"),
	OCIRC2('ô', "ocirc", "244", "F4"),
	ODASH('⊝', "odash", "8861", "229D"),
	ODBLAC('Ő', "Odblac", "336", "150"),
	ODBLAC2('ő', "odblac", "337", "151"),
	ODOT('⊙', "odot", "8857", "2299"),
	OELIG('Œ', "OElig", "338", "152"),
	OELIG2('œ', "oelig", "339", "153"),
	OGRAVE('Ò', "Ograve", "210", "D2"),
	OGRAVE2('ò', "ograve", "242", "F2"),
	OLARR('↺', "olarr", "8634", "21BA"),
	OLINE('‾', "oline", "8254", "203E"),
	OMACR('Ō', "Omacr", "332", "14C"),
	OMACR2('ō', "omacr", "333", "14D"),
	OMEGA('Ω', "Omega", "937", "3A9"),
	OMEGA2('ω', "omega", "969", "3C9"),
	OMICRON('Ο', "Omicron", "927", "39F"),
	OMICRON2('ο', "omicron", "959", "3BF"),
	OMINUS('⊖', "ominus", "8854", "2296"),
	OPLUS('⊕', "oplus", "8853", "2295"),
	OR('∨', "or", "8744", "2228"),
	ORARR('↻', "orarr", "8635", "21BB"),
	ORDF('ª', "ordf", "170", "AA"),
	ORDM('º', "ordm", "186", "BA"),
	ORIGOF('⊶', "origof", "8886", "22B6"),
	OSCR('ℴ', "oscr", "8500", "2134"),
	OSLASH('Ø', "Oslash", "216", "D8"),
	OSLASH2('ø', "oslash", "248", "F8"),
	OSOL('⊘', "osol", "8856", "2298"),
	OTILDE('Õ', "Otilde", "213", "D5"),
	OTILDE2('õ', "otilde", "245", "F5"),
	OTIMES('⊗', "otimes", "8855", "2297"),
	OUML('Ö', "Ouml", "214", "D6"),
	OUML2('ö', "ouml", "246", "F6"),
	PARA('¶', "para", "182", "B6"),
	PARALLEL('∥', "parallel", "8741", "2225"),
	PART('∂', "part", "8706", "2202"),
	PERCNT('%', "percnt", "37", "25"),
	PERIOD('.', "period", "46", "2E"),
	PERMIL('', "permil", "137", "89"),
	PERMIL2('‰', "permil", "8240", "2030"),
	PERP('⊥', "perp", "8869", "22A5"),
	PERTENK('‱', "pertenk", "8241", "2031"),
	PHI('Φ', "Phi", "934", "3A6"),
	PHI2('φ', "phi", "966", "3C6"),
	PHONE('☎', "phone", "9742", "260E"),
	PI('Π', "Pi", "928", "3A0"),
	PI2('π', "pi", "960", "3C0"),
	PIV('ϖ', "piv", "982", "3D6"),
	PLANCK('ℏ', "planck", "8463", "210F"),
	PLANCKH('ℎ', "planckh", "8462", "210E"),
	PLUS('+', "plus", "43", "2B"),
	PLUSB('⊞', "plusb", "8862", "229E"),
	PLUSDO('∔', "plusdo", "8724", "2214"),
	PLUSMN('±', "plusmn", "177", "B1"),
	POPF('ℙ', "Popf", "8473", "2119"),
	POUND('£', "pound", "163", "A3"),
	PR('≺', "pr", "8826", "227A"),
	PRCUE('≼', "prcue", "8828", "227C"),
	PRIME('′', "prime", "8242", "2032"),
	PRIME2('″', "Prime", "8243", "2033"),
	PRNSIM('⋨', "prnsim", "8936", "22E8"),
	PROD('∏', "prod", "8719", "220F"),
	PROP('∝', "prop", "8733", "221D"),
	PRSIM('≾', "prsim", "8830", "227E"),
	PRUREL('⊰', "prurel", "8880", "22B0"),
	PSI('Ψ', "Psi", "936", "3A8"),
	PSI2('ψ', "psi", "968", "3C8"),
	QOPF('ℚ', "Qopf", "8474", "211A"),
	QPRIME('⁗', "qprime", "8279", "2057"),
	QUEST('?', "quest", "63", "3F"),
	QUOT('"', "quot", "34", "22"),
	RAARR('⇛', "rAarr", "8667", "21DB"),
	RACUTE('Ŕ', "Racute", "340", "154"),
	RACUTE2('ŕ', "racute", "341", "155"),
	RADIC('√', "radic", "8730", "221A"),
	RANG('〉', "rang", "9002", "232A"),
	RAQUO('»', "raquo", "187", "BB"),
	RARR('⇒', "rArr", "8658", "21D2"),
	RARRAP('⥵', "rarrap", "10613", "2975"),
	RARRB('⇥', "rarrb", "8677", "21E5"),
	RARRBFS('⤠', "rarrbfs", "10528", "2920"),
	RARRFS('⤞', "rarrfs", "10526", "291E"),
	RARRPL('⥅', "rarrpl", "10565", "2945"),
	RARRSIM('⥴', "rarrsim", "10612", "2974"),
	RARRTL('⤖', "Rarrtl", "10518", "2916"),
	RATAIL('⤚', "ratail", "10522", "291A"),
	RATAIL2('⤜', "rAtail", "10524", "291C"),
	RATIO('∶', "ratio", "8758", "2236"),
	RBARR('⤍', "rbarr", "10509", "290D"),
	RBARR2('⤏', "rBarr", "10511", "290F"),
	RBARR3('⤐', "RBarr", "10512", "2910"),
	RBBRK('❳', "rbbrk", "10099", "2773"),
	RBRACE('}', "rbrace", "125", "7D"),
	RBRACK(']', "rbrack", "93", "5D"),
	RCARON('Ř', "Rcaron", "344", "158"),
	RCARON2('ř', "rcaron", "345", "159"),
	RCEDIL('Ŗ', "Rcedil", "342", "156"),
	RCEDIL2('ŗ', "rcedil", "343", "157"),
	RCEIL('⌉', "rceil", "8969", "2309"),
	RDCA('⤷', "rdca", "10551", "2937"),
	RDLDHAR('⥩', "rdldhar", "10601", "2969"),
	RDQUO('”', "rdquo", "8221", "201D"),
	REAL('ℜ', "real", "8476", "211C"),
	REG('®', "reg", "174", "AE"),
	RFISHT('⥽', "rfisht", "10621", "297D"),
	RFLOOR('⌋', "rfloor", "8971", "230B"),
	RHAR('⥤', "rHar", "10596", "2964"),
	RHARD('⇁', "rhard", "8641", "21C1"),
	RHARU('⇀', "rharu", "8640", "21C0"),
	RHARUL('⥬', "rharul", "10604", "296C"),
	RHO('Ρ', "Rho", "929", "3A1"),
	RHO2('ρ', "rho", "961", "3C1"),
	RIGHTDOWNTEEVECTOR('⥝', "RightDownTeeVector", "10589", "295D"),
	RIGHTDOWNVECTORBAR('⥕', "RightDownVectorBar", "10581", "2955"),
	RIGHTTEEVECTOR('⥛', "RightTeeVector", "10587", "295B"),
	RIGHTUPDOWNVECTOR('⥏', "RightUpDownVector", "10575", "294F"),
	RIGHTUPTEEVECTOR('⥜', "RightUpTeeVector", "10588", "295C"),
	RIGHTUPVECTORBAR('⥔', "RightUpVectorBar", "10580", "2954"),
	RIGHTVECTORBAR('⥓', "RightVectorBar", "10579", "2953"),
	RLARR('⇄', "rlarr", "8644", "21C4"),
	RLHAR('⇌', "rlhar", "8652", "21CC"),
	RLM('‏', "rlm", "8207", "200F"),
	ROARR('⇾', "roarr", "8702", "21FE"),
	ROPF('ℝ', "Ropf", "8477", "211D"),
	ROUNDIMPLIES('⥰', "RoundImplies", "10608", "2970"),
	RPAR(')', "rpar", "41", "29"),
	RRARR('⇉', "rrarr", "8649", "21C9"),
	RSAQUO('›', "rsaquo", "8250", "203A"),
	RSCR('ℛ', "Rscr", "8475", "211B"),
	RSQUO('’', "rsquo", "8217", "2019"),
	RTHREE('⋌', "rthree", "8908", "22CC"),
	RTIMES('⋊', "rtimes", "8906", "22CA"),
	RTRIE('⊵', "rtrie", "8885", "22B5"),
	RULUHAR('⥨', "ruluhar", "10600", "2968"),
	RX('℞', "rx", "8478", "211E"),
	SACUTE('Ś', "Sacute", "346", "15A"),
	SACUTE2('ś', "sacute", "347", "15B"),
	SBQUO('‚', "sbquo", "8218", "201A"),
	SC('≻', "sc", "8827", "227B"),
	SCARON('Š', "Scaron", "352", "160"),
	SCARON2('š', "scaron", "353", "161"),
	SCCUE('≽', "sccue", "8829", "227D"),
	SCEDIL('Ş', "Scedil", "350", "15E"),
	SCEDIL2('ş', "scedil", "351", "15F"),
	SCIRC('Ŝ', "Scirc", "348", "15C"),
	SCIRC2('ŝ', "scirc", "349", "15D"),
	SCNSIM('⋩', "scnsim", "8937", "22E9"),
	SCSIM('≿', "scsim", "8831", "227F"),
	SDOT('⋅', "sdot", "8901", "22C5"),
	SDOTB('⊡', "sdotb", "8865", "22A1"),
	SEARHK('⤥', "searhk", "10533", "2925"),
	SEARR('⇘', "seArr", "8664", "21D8"),
	SECT('§', "sect", "167", "A7"),
	SEMI(';', "semi", "59", "3B"),
	SESWAR('⤩', "seswar", "10537", "2929"),
	SETMINUS('∖', "setminus", "8726", "2216"),
	SEXT('✶', "sext", "10038", "2736"),
	SHARP('♯', "sharp", "9839", "266F"),
	SHY('­', "shy", "173", "AD"),
	SIGMA('Σ', "Sigma", "931", "3A3"),
	SIGMA2('σ', "sigma", "963", "3C3"),
	SIGMAF('ς', "sigmaf", "962", "3C2"),
	SIM('∼', "sim", "8764", "223C"),
	SIME('≃', "sime", "8771", "2243"),
	SIMNE('≆', "simne", "8774", "2246"),
	SIMRARR('⥲', "simrarr", "10610", "2972"),
	SOL('/', "sol", "47", "2F"),
	SPADES('♠', "spades", "9824", "2660"),
	SQCAP('⊓', "sqcap", "8851", "2293"),
	SQCUP('⊔', "sqcup", "8852", "2294"),
	SQSUB('⊏', "sqsub", "8847", "228F"),
	SQSUBE('⊑', "sqsube", "8849", "2291"),
	SQSUP('⊐', "sqsup", "8848", "2290"),
	SQSUPE('⊒', "sqsupe", "8850", "2292"),
	STAR('⋆', "Star", "8902", "22C6"),
	STAR2('☆', "star", "9734", "2606"),
	STARF('★', "starf", "9733", "2605"),
	SUB('⊂', "sub", "8834", "2282"),
	SUB2('⋐', "Sub", "8912", "22D0"),
	SUBE('⊆', "sube", "8838", "2286"),
	SUBNE('⊊', "subne", "8842", "228A"),
	SUBRARR('⥹', "subrarr", "10617", "2979"),
	SUM('∑', "sum", "8721", "2211"),
	SUNG('♪', "sung", "9834", "266A"),
	SUP1('¹', "sup1", "185", "B9"),
	SUP2('²', "sup2", "178", "B2"),
	SUP3('³', "sup3", "179", "B3"),
	SUP('⊃', "sup", "8835", "2283"),
	SUP4('⋑', "Sup", "8913", "22D1"),
	SUPE('⊇', "supe", "8839", "2287"),
	SUPLARR('⥻', "suplarr", "10619", "297B"),
	SUPNE('⊋', "supne", "8843", "228B"),
	SWARHK('⤦', "swarhk", "10534", "2926"),
	SWARR('⇙', "swArr", "8665", "21D9"),
	SWNWAR('⤪', "swnwar", "10538", "292A"),
	SZLIG('ß', "szlig", "223", "DF"),
	TAU('Τ', "Tau", "932", "3A4"),
	TAU2('τ', "tau", "964", "3C4"),
	TCARON('Ť', "Tcaron", "356", "164"),
	TCARON2('ť', "tcaron", "357", "165"),
	TCEDIL('Ţ', "Tcedil", "354", "162"),
	TCEDIL2('ţ', "tcedil", "355", "163"),
	THERE4('∴', "there4", "8756", "2234"),
	THETA('Θ', "Theta", "920", "398"),
	THETA2('θ', "theta", "952", "3B8"),
	THETASYM('ϑ', "thetasym", "977", "3D1"),
	THINSP(' ', "thinsp", "8201", "2009"),
	THORN('Þ', "THORN", "222", "DE"),
	THORN2('þ', "thorn", "254", "FE"),
	TILDE('~', "tilde", "126", "7E"),
	TIMES('×', "times", "215", "D7"),
	TIMESB('⊠', "timesb", "8864", "22A0"),
	TOP('⊤', "top", "8868", "22A4"),
	TPRIME('‴', "tprime", "8244", "2034"),
	TRADE('™', "trade", "8482", "2122"),
	TRIE('≜', "trie", "8796", "225C"),
	TSTROK('Ŧ', "Tstrok", "358", "166"),
	TSTROK2('ŧ', "tstrok", "359", "167"),
	UACUTE('Ú', "Uacute", "218", "DA"),
	UACUTE2('ú', "uacute", "250", "FA"),
	UARR('⇑', "uArr", "8657", "21D1"),
	UARROCIR('⥉', "Uarrocir", "10569", "2949"),
	UBREVE('Ŭ', "Ubreve", "364", "16C"),
	UBREVE2('ŭ', "ubreve", "365", "16D"),
	UCIRC('Û', "Ucirc", "219", "DB"),
	UCIRC2('û', "ucirc", "251", "FB"),
	UDARR('⇅', "udarr", "8645", "21C5"),
	UDBLAC('Ű', "Udblac", "368", "170"),
	UDBLAC2('ű', "udblac", "369", "171"),
	UDHAR('⥮', "udhar", "10606", "296E"),
	UFISHT('⥾', "ufisht", "10622", "297E"),
	UGRAVE('Ù', "Ugrave", "217", "D9"),
	UGRAVE2('ù', "ugrave", "249", "F9"),
	UHAR('⥣', "uHar", "10595", "2963"),
	UHARL('↿', "uharl", "8639", "21BF"),
	UHARR('↾', "uharr", "8638", "21BE"),
	UMACR('Ū', "Umacr", "362", "16A"),
	UMACR2('ū', "umacr", "363", "16B"),
	UML('¨', "uml", "168", "A8"),
	UOGON('Ų', "Uogon", "370", "172"),
	UOGON2('ų', "uogon", "371", "173"),
	UPARROWBAR('⤒', "UpArrowBar", "10514", "2912"),
	UPLUS('⊎', "uplus", "8846", "228E"),
	UPSIH('ϒ', "upsih", "978", "3D2"),
	UPSILON('Υ', "Upsilon", "933", "3A5"),
	UPSILON2('υ', "upsilon", "965", "3C5"),
	URING('Ů', "Uring", "366", "16E"),
	URING2('ů', "uring", "367", "16F"),
	UTDOT('⋰', "utdot", "8944", "22F0"),
	UTILDE('Ũ', "Utilde", "360", "168"),
	UTILDE2('ũ', "utilde", "361", "169"),
	UUARR('⇈', "uuarr", "8648", "21C8"),
	UUML('Ü', "Uuml", "220", "DC"),
	UUML2('ü', "uuml", "252", "FC"),
	VARR('⇕', "vArr", "8661", "21D5"),
	VDASH('⊢', "vdash", "8866", "22A2"),
	VDASH2('⊨', "vDash", "8872", "22A8"),
	VDASH3('⊩', "Vdash", "8873", "22A9"),
	VDASH4('⊫', "VDash", "8875", "22AB"),
	VEEBAR('⊻', "veebar", "8891", "22BB"),
	VEEEQ('≚', "veeeq", "8794", "225A"),
	VELLIP('⋮', "vellip", "8942", "22EE"),
	VERT('|', "vert", "124", "7C"),
	VERT2('‖', "Vert", "8214", "2016"),
	VERTICALSEPARATOR('❘', "VerticalSeparator", "10072", "2758"),
	VLTRI('⊲', "vltri", "8882", "22B2"),
	VRTRI('⊳', "vrtri", "8883", "22B3"),
	VVDASH('⊪', "Vvdash", "8874", "22AA"),
	WCIRC('Ŵ', "Wcirc", "372", "174"),
	WCIRC2('ŵ', "wcirc", "373", "175"),
	WEDGEQ('≙', "wedgeq", "8793", "2259"),
	WEIERP('℘', "weierp", "8472", "2118"),
	WREATH('≀', "wreath", "8768", "2240"),
	XCAP('⋂', "xcap", "8898", "22C2"),
	XCUP('⋃', "xcup", "8899", "22C3"),
	XHARR('⟷', "xharr", "10231", "27F7"),
	XHARR2('⟺', "xhArr", "10234", "27FA"),
	XI('Ξ', "Xi", "926", "39E"),
	XI2('ξ', "xi", "958", "3BE"),
	XLARR('⟵', "xlarr", "10229", "27F5"),
	XLARR2('⟸', "xlArr", "10232", "27F8"),
	XMAP('⟼', "xmap", "10236", "27FC"),
	XNIS('⋻', "xnis", "8955", "22FB"),
	XRARR('⟶', "xrarr", "10230", "27F6"),
	XRARR2('⟹', "xrArr", "10233", "27F9"),
	XVEE('⋁', "xvee", "8897", "22C1"),
	XWEDGE('⋀', "xwedge", "8896", "22C0"),
	YACUTE('Ý', "Yacute", "221", "DD"),
	YACUTE2('ý', "yacute", "253", "FD"),
	YCIRC('Ŷ', "Ycirc", "374", "176"),
	YCIRC2('ŷ', "ycirc", "375", "177"),
	YEN('¥', "yen", "165", "A5"),
	YUML('ÿ', "yuml", "255", "FF"),
	YUML2('Ÿ', "Yuml", "376", "178"),
	ZACUTE('Ź', "Zacute", "377", "179"),
	ZACUTE2('ź', "zacute", "378", "17A"),
	ZCARON('Ž', "Zcaron", "381", "17D"),
	ZCARON2('ž', "zcaron", "382", "17E"),
	ZDOT('Ż', "Zdot", "379", "17B"),
	ZDOT2('ż', "zdot", "380", "17C"),
	ZETA('Ζ', "Zeta", "918", "396"),
	ZETA2('ζ', "zeta", "950", "3B6"),
	ZFR('ℨ', "Zfr", "8488", "2128"),
	ZIGLARR('⇜', "ziglarr", "8668", "21DC"),
	ZIGRARR('⇝', "zigrarr", "8669", "21DD"),
	ZOPF('ℤ', "Zopf", "8484", "2124"),
	ZWJ('‍', "zwj", "8205", "200D"),
	ZWNJ('‌', "zwnj", "8204", "200C");

	/**
	 * Специальный символ.
	 */
	@NotNull
	private final Character symbol;

	/**
	 * HTML-сущностейа специального символа в виде имени.
	 */
	@NotNull
	private final String name;

	/**
	 * HTML-сущностейа специального символа в виде числа.
	 */
	@NotNull
	private final String numeric;

	/**
	 * HTML-сущностейа специального символа в виде unicode.
	 */
	@NotNull
	private final String unicode;

	/**
	 * Конструктор для {@code Entity}.
	 *
	 * @param symbol Специальный символ.
	 * @param name HTML-сущностейа специального символа в виде имени.
	 * @param numeric HTML-сущностейа специального символа в виде числа.
	 * @param unicode HTML-сущностейа специального символа в виде unicode.
	 */
	Entity(@NotNull final Character symbol,
		   @NotNull final String name,
		   @NotNull final String numeric,
		   @NotNull final String unicode)
	{
		this.symbol = symbol;
		this.name = name;
		this.numeric = numeric;
		this.unicode = unicode;
	}

	/**
	 * Возвращает специальный символ.
	 *
	 * @return Специальный символ.
	 */
	@NotNull
	public Character getSymbol()
	{
		return this.symbol;
	}

	/**
	 * Возвращает HTML-сущность специального символа в указанном типе.
	 *
	 * @param entityType Тип сущности.
	 * @return HTML-сущность специального символа.
	 */
	@NotNull
	public String getEntity(@NotNull final EntityType entityType)
	{
		return switch (entityType)
			{
				case NAME: yield this.getEntityName();
				case NUMERIC: yield this.getEntityNumeric();
				case UNICODE: yield this.getEntityUnicode();
			};
	}

	/**
	 * Возвращает HTML-сущность специального символа в виде имени.
	 *
	 * @return HTML-сущность специального символа в виде имени.
	 */
	@NotNull
	public String getEntityName()
	{
		return this.name;
	}

	/**
	 * Возвращает HTML-сущность специального символа в виде числа.
	 *
	 * @return HTML-сущность специального символа в виде числа.
	 */
	@NotNull
	public String getEntityNumeric()
	{
		return this.numeric;
	}

	/**
	 * Возвращает HTML-сущность специального символа в виде unicode.
	 *
	 * @return HTML-сущность специального символа в виде unicode.
	 */
	@NotNull
	public String getEntityUnicode()
	{
		return this.unicode;
	}

	/**
	 * Возвращает HTML-сущностейу специального символа в указанном типе.
	 *
	 * @return HTML-сущностейа специального символа.
	 */
	@NotNull
	public String getMnemonic(@NotNull final EntityType entityType)
	{
		return switch (entityType)
			{
				case NAME: yield this.getMnemonicName();
				case NUMERIC: yield this.getMnemonicNumeric();
				case UNICODE: yield this.getMnemonicUnicode();
			};
	}

	/**
	 * Возвращает HTML-сущностейу специального символа в виде имени.
	 *
	 * @return HTML-сущностейа специального символа в виде имени.
	 */
	@NotNull
	public String getMnemonicName()
	{
		return "&" + this.name + ";";
	}

	/**
	 * Возвращает HTML-сущностейа специального символа в виде числа.
	 *
	 * @return HTML-сущностейа специального символа в виде числа.
	 */
	@NotNull
	public String getMnemonicNumeric()
	{
		return "&#" + this.numeric + ";";
	}

	/**
	 * Возвращает HTML-сущностейа специального символа в виде unicode.
	 *
	 * @return HTML-сущностейа специального символа в виде unicode.
	 */
	@NotNull
	public String getMnemonicUnicode()
	{
		return "&#X" + this.unicode + ";";
	}
}
