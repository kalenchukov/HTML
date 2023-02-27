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
 * Перечисление шаблонов регулярных выражений.
 */
public enum Regexp
{
	/**
	 * Область CDATA.
	 *
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Должен начинаться со строки "&lt;![CDATA[";</li>
	 * 		<li>Не может содержать строк "]]&gt;";</li>
	 * 		<li>Должен заканчиваться строкой "]]&gt;".</li>
	 * </ul>
	 */
	CDATA("cdata", """
		(?<cdata>
			<!\\[CDATA\\[
			(?<value>((?!\\]\\]>).)*?)
			\\]\\]>
		)
		"""),

	/**
	 * Самозакрывающийся HTML-тег.
	 *
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Должен начинаться с символа "&lt;";</li>
	 * 		<li>Название тега должно состоять из букв;</li>
	 * 		<li>Название параметра должно состоять из букв, цифр и символов "-", "_";</li>
	 * 		<li>Значение параметра в двойных кавычках не может содержать символ "&quot;";</li>
	 * 		<li>Значение параметра в одинарных кавычках не может содержать символы "&#39;";</li>
	 * 		<li>Значение параметра без кавычек не может содержать пробелы и символы "&quot;", "&#39;", "&lt;", "&gt;", "&#96;";</li>
	 * 		<li>Должен заканчиваться строкой "/&gt;".</li>
	 * </ul>
	 */
	SELF_CLOSING_TAG("tag", """
		(?<tag>
		<
		(?<name>[a-z]+)
		(?<params>
			(
				\\s+
				[0-9a-z\\-_]+
				(
					\\s*=\\s*
					(
						(
							(?:")
							[^"]*
							(?:")
						)
						|
						(
							(?:')
							[^']*
							(?:')
						)
						|
						(
							[^"'=\\s<>`]+
						)
					)
				)?
			)+
		)*
		\\s*
		/>
		)
		"""),

	/**
	 * Открывающий HTML-тег.
	 *
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Должен начинаться с символа "&lt;";</li>
	 * 		<li>Название тега должно состоять из букв;</li>
	 * 		<li>Название параметра должно состоять из букв, цифр и символов "-", "_";</li>
	 * 		<li>Значение параметра в двойных кавычках не может содержать символ "&quot;";</li>
	 * 		<li>Значение параметра в одинарных кавычках не может содержать символы "&#39;";</li>
	 * 		<li>Значение параметра без кавычек не может содержать пробелы и символы "&quot;", "&#39;", "&lt;", "&gt;", "&#96;";</li>
	 * 		<li>Должен заканчиваться символом "&gt;".</li>
	 * </ul>
	 */
	OPEN_TAG("tag", """
		(?<tag>
			<
			(?<name>[a-z]+)
			(?<params>
				(
					\\s+
					[0-9a-z\\-_]+
					(
						\\s*=\\s*
						(
							(
								(?:")
								[^"]*
								(?:")
							)
							|
							(
								(?:')
								[^']*
								(?:')
							)
							|
							(
								[^"'=\\s<>`/]+
							)
						)
					)?
				)+
			)*
			\\s*
			>
		)
		"""),

	/**
	 * Закрывающий HTML-тег.
	 *
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Должен начинаться со строки "&lt;/";</li>
	 * 		<li>Должен состоять из букв;</li>
	 * 		<li>Должен заканчиваться символом "&gt;".</li>
	 * </ul>
	 */
	CLOSE_TAG("tag", """
		(?<tag>
			</
			(?<name>[a-z]+)
			\\s*
			>
		)
		"""),

	/**
	 * HTML-комментарий.
	 *
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Должен начинаться со строки "&lt;!--";</li>
	 * 		<li>Не может начинаться с символа "&lt;";</li>
	 * 		<li>Не может начинаться со строки "-&gt;";</li>
	 * 		<li>Не может содержать строк "&lt;!--", "--&gt;", "--!&gt;";</li>
	 * 		<li>Не может заканчиваться строкой "&lt;!-";</li>
	 * 		<li>Должен заканчиваться строкой "--&gt;".</li>
	 * </ul>
	 */
	COMMENT("comment", """
		(?<comment>
			<!--
			(?!(<|->))
			(?<value>((?!<!--|-->|--!>).)*?)
			(?!(<!-))
			-->
		)
		"""),

	/**
	 * Тип HTML-документа.
	 *
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Должен начинаться со строки "&lt;!DOCTYPE";</li>
	 * 		<li>Корневой элемент должен быть из букв;</li>
	 * 		<li>Публичность должна быть из букв;</li>
	 * 		<li>DTD должно быть заключён в одинарные или двойные кавычки;</li>
	 * 		<li>Регистрация должна быть из символа "+" или "-";</li>
	 * 		<li>Название организации должно быть из букв, цифр, пробела и символов "." и "-";</li>
	 * 		<li>Тип документа должно быть из букв, цифр, пробела и символа ".";</li>
	 * 		<li>Код языка должен быть из букв;</li>
	 * 		<li>URL должен быть заключён в одинарные или двойные кавычки;</li>
	 * 		<li>URL должен быть из любых символов;</li>
	 * 		<li>Должен заканчиваться символом "&gt;".</li>
	 * </ul>
	 */
	DOCTYPE("doctype", """
		(?<doctype>
			<!DOCTYPE
			\\s+
			(?<rootElement>[A-Z]+)
			(
				\\s+
				(?<public>[A-Z]+)
				\\s+
				("|')
				(?<dtd>
					(?<registration>[+-])
					//
					(?<organization>[0-9A-Z.\\-\\s]+)
					//
					(?<documentType>[0-9A-Z.\\s]+)
					//
					(?<language>[A-Z]+)
				)
				\\5
				\\s*
				("|')
				(?<url>[^"']+?)
				\\11
			)?
			\\s*
			>
		)
		"""),

	/**
	 * HTML-сущность в виде имени.
	 *
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Должен начинаться с символа "&amp;";</li>
	 * 		<li>Не может начинаться c цифр;</li>
	 * 		<li>Не может быть только из цифр;</li>
	 * 		<li>Может состоять из букв и цифр;</li>
	 * 		<li>Не может быть меньше 4 символов;</li>
	 * 		<li>Должен заканчиваться символом ";".</li>
	 * </ul>
	 */
	ENTITY_NAME("entity", """
		(?<entity>
			(?=.{4,})
			&
			(?![0-9]+)
			(?<name>[0-9A-Z]+)
			;
		)
		"""),

	/**
	 * HTML-сущность в виде unicode.
	 *
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Должен начинаться со строки "&amp;#X";</li>
	 * 		<li>Может состоять из диапазона букв A-F и цифр;</li>
	 * 		<li>Не может быть меньше 6 символов;</li>
	 * 		<li>Должен заканчиваться символом ";".</li>
	 * </ul>
	 */
	ENTITY_UNICODE("entity", """
		(?<entity>
			(?=.{6,})
			&#X
			(?<unicode>
				0*
				(?<unicodeLeast>[0-9A-F]+)
			)
			;
		)
		"""),

	/**
	 * HTML-сущность в виде числа.
	 *
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Должен начинаться со строки "&amp;#";</li>
	 * 		<li>Может состоять из цифр;</li>
	 * 		<li>Не может быть меньше 5 символов;</li>
	 * 		<li>Должен заканчиваться символом ";".</li>
	 * </ul>
	 */
	ENTITY_NUMERIC("entity", """
		(?<entity>
			(?=.{5,})
			&#
			(?<numeric>
				0*
				(?<numericLeast>[0-9]+)
			)
			;
		)
		""");

	/**
	 * Основная группа регулярного выражения.
	 */
	@NotNull
	private final String group;

	/**
	 * Шаблон регулярного выражения.
	 */
	@NotNull
	private final String pattern;

	/**
	 * Конструктор для {@code Regexp}.
	 *
	 * @param pattern шаблон регулярного выражения.
	 */
	Regexp(@NotNull final String group, @NotNull final String pattern)
	{
		this.group = group;
		this.pattern = pattern;
	}

	/**
	 * Возвращает основную группу регулярного выражения.
	 *
	 * @return основную группу регулярного выражения.
	 */
	@NotNull
	public String getGroup()
	{
		return this.group;
	}

	/**
	 * Возвращает шаблон регулярного выражения.
	 *
	 * @return шаблон регулярного выражения.
	 */
	@NotNull
	public String getPattern()
	{
		return this.pattern.replaceAll("[\n\t\s]*", "");
	}
}
