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

import dev.kalenchukov.html.resources.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

/**
 * Интерфейс для реализации класса работы с гипертекстом.
 */
public interface Hipertext
{
	/**
	 * Устанавливает текст для обработки.
	 *
	 * @param text текст для обработки.
	 */
	void setText(@NotNull String text);

	/**
	 * Возвращает обработанный текст.
	 *
	 * @return обработанный текст.
	 */
	String getText();

	/**
	 * Удаляет HTML-комментарии.
	 */
	void deleteComments();

	/**
	 * Удаляет разделы CDATA.
	 */
	void deleteCData();

	/**
	 * Удаляет тип документа.
	 */
	void deleteDoctype();

	/**
	 * Удаляет HTML-тег.
	 *
	 * @param tag HTML-тег.
	 */
	void deleteTag(@NotNull Tag tag);

	/**
	 * Удаляет HTML-тег.
	 *
	 * @param tag HTML-тег.
	 * @param tagType тип HTML-тега.
	 */
	void deleteTag(@NotNull Tag tag, @NotNull TagType tagType);

	/**
	 * Удаляет все HTML-теги.
	 */
	void deleteTags();

	/**
	 * Удаляет все HTML-теги.
	 *
	 * @param tagType тип HTML-тега.
	 */
	void deleteTags(@NotNull TagType tagType);

	/**
	 * Удаляет все HTML-теги.
	 *
	 * @param excludeTags коллекция HTML-тегов которые не будут удалены.
	 */
	void deleteTags(@NotNull List<@NotNull Tag> excludeTags);

	/**
	 * Удаляет все HTML-теги.
	 *
	 * @param tagType тип HTML-тега.
	 * @param excludeTags коллекция HTML-тегов которые не будут удалены.
	 */
	void deleteTags(@NotNull TagType tagType, @NotNull List<@NotNull Tag> excludeTags);

	/**
	 * Удаляет HTML-сущность.
	 *
	 * @param entity HTML-сущность.
	 */
	void deleteEntity(@NotNull Entity entity);

	/**
	 * Удаляет HTML-сущность.
	 *
	 * @param entity HTML-сущность.
	 * @param entityType тип HTML-сущностей.
	 */
	void deleteEntity(@NotNull Entity entity, @NotNull EntityType entityType);

	/**
	 * Удаляет все HTML-сущности.
	 */
	void deleteEntities();

	/**
	 * Удаляет все HTML-сущности.
	 *
	 * @param excludeEntities коллекция HTML-сущностей которые не будут удалены.
	 */
	void deleteEntities(@NotNull List<@NotNull Entity> excludeEntities);

	/**
	 * Преобразует специальный символ на соответствующую HTML-сущность.
	 *
	 * @param entity символ.
	 * @param entityType тип HTML-сущностей.
	 */
	void encodeEntity(@NotNull Entity entity, @NotNull EntityType entityType);

	/**
	 * Преобразует все специальные символы на соответствующие HTML-сущности.
	 *
	 * @param entityType тип HTML-сущностей.
	 */
	void encodeEntities(@NotNull EntityType entityType);

	/**
	 * Преобразует все специальные символы на соответствующие HTML-сущности.
	 *
	 * @param entityType тип HTML-сущностей.
	 * @param excludeEntities коллекция символов которые не будут преобразованы.
	 */
	void encodeEntities(@NotNull EntityType entityType, @NotNull List<@NotNull Entity> excludeEntities);

	/**
	 * Преобразует HTML-сущность в соответствующий специальный символ.
	 *
	 * @param entity HTML-сущность.
	 */
	void decodeEntity(@NotNull Entity entity);

	/**
	 * Преобразует HTML-сущность в соответствующий специальный символ.
	 *
	 * @param entity HTML-сущность.
	 * @param entityType тип HTML-сущностей.
	 */
	void decodeEntity(@NotNull Entity entity, @NotNull EntityType entityType);

	/**
	 * Преобразует все HTML-сущности в соответствующие специальные символы.
	 */
	void decodeEntities();

	/**
	 * Преобразует все HTML-сущности в соответствующие специальные символы.
	 *
	 * @param entityType тип HTML-сущностей.
	 */
	void decodeEntities(@NotNull EntityType entityType);

	/**
	 * Преобразует все HTML-сущности в соответствующие специальные символы.
	 *
	 * @param excludeEntities коллекция HTML-сущностей которые не будут преобразованы.
	 */
	void decodeEntities(@NotNull List<@NotNull Entity> excludeEntities);

	/**
	 * Преобразует все HTML-сущности в соответствующие специальные символы.
	 *
	 * @param excludeEntities коллекция HTML-сущностей которые не будут преобразованы.
	 * @param entityType тип HTML-сущностей.
	 */
	void decodeEntities(@NotNull EntityType entityType, @NotNull List<@NotNull Entity> excludeEntities);

	/**
	 * Проверяет, является ли строка HTML комментарием.
	 *
	 * @return {@code true}, если строка является HTML комментарием, иначе {@code false}.
	 */
	boolean isComment();

	/**
	 * Проверяет, является ли строка HTML сущностью в виде имени.
	 *
	 * @return {@code true}, если строка является HTML сущностью в виде имени, иначе {@code false}.
	 */
	boolean isEntityName();

	/**
	 * Проверяет, является ли строка HTML сущностью в виде числа.
	 *
	 * @return {@code true}, если строка является HTML сущностью в виде числа, иначе {@code false}.
	 */
	boolean isEntityNumeric();

	/**
	 * Проверяет, является ли строка HTML сущностью в виде unicode.
	 *
	 * @return {@code true}, если строка является HTML сущностью в виде unicode, иначе {@code false}.
	 */
	boolean isEntityUnicode();

	/**
	 * Проверяет, является ли строка типом HTML документа.
	 *
	 * @return {@code true}, если строка является типом HTML документа, иначе {@code false}.
	 */
	boolean isDoctype();

	/**
	 * Проверяет, является ли строка областью CDATA.
	 *
	 * @return {@code true}, если строка является областью CDATA, иначе {@code false}.
	 */
	boolean isCData();

	/**
	 * Проверяет, является ли строка закрывающим HTML тегом.
	 *
	 * @return {@code true}, если строка является закрывающим HTML тегом, иначе {@code false}.
	 */
	boolean isCloseTag();

	/**
	 * Проверяет, является ли строка открывающим HTML тегом.
	 *
	 * @return {@code true}, если строка является открывающим HTML тегом, иначе {@code false}.
	 */
	boolean isOpenTag();

	/**
	 * Проверяет, является ли строка самозакрывающимся HTML тегом.
	 *
	 * @return {@code true}, если строка является самозакрывающимся HTML тегом, иначе {@code false}.
	 */
	boolean isSelfClosingTag();

	/**
	 * Выполняет поиск самозакрывающихся HTML тегов.
	 *
	 * @return коллекцию с найденными самозакрывающихся HTML тегами.
	 */
	@Unmodifiable
	@NotNull
	List<@NotNull String> findSelfClosingTag();

	/**
	 * Выполняет поиск открывающих HTML тегов.
	 *
	 * @return коллекцию с найденными открывающими HTML тегами.
	 */
	@Unmodifiable
	@NotNull
	List<@NotNull String> findOpenTag();

	/**
	 * Выполняет поиск закрывающих HTML тегов.
	 *
	 * @return коллекцию с найденными закрывающими HTML тегами.
	 */
	@Unmodifiable
	@NotNull
	List<@NotNull String> findCloseTag();

	/**
	 * Выполняет поиск областей CDATA.
	 *
	 * @return коллекцию с найденными областями CDATA.
	 */
	@Unmodifiable
	@NotNull
	List<@NotNull String> findCData();

	/**
	 * Выполняет поиск типов HTML документа.
	 *
	 * @return коллекцию с найденными типами HTML документа.
	 */
	@Unmodifiable
	@NotNull
	List<@NotNull String> findDoctype();

	/**
	 * Выполняет поиск HTML-сущностей в виде имени.
	 *
	 * @return коллекцию с найденными HTML сущностями в виде имени.
	 */
	@Unmodifiable
	@NotNull
	List<@NotNull String> findEntityName();

	/**
	 * Выполняет поиск HTML-сущностей в виде числа.
	 *
	 * @return коллекцию с найденными HTML сущностями в виде числа.
	 */
	@Unmodifiable
	@NotNull
	List<@NotNull String> findEntityNumeric();

	/**
	 * Выполняет поиск HTML-сущностей в виде unicode.
	 *
	 * @return коллекцию с найденными HTML сущностями в виде unicode.
	 */
	@Unmodifiable
	@NotNull
	List<@NotNull String> findEntityUnicode();

	/**
	 * Выполняет поиск HTML комментариев.
	 *
	 * @return коллекцию с найденными HTML комментариями.
	 */
	@Unmodifiable
	@NotNull
	List<@NotNull String> findComment();
}
