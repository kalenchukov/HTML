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
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Интерфейс для реализации класса работы с гипертекстом.
 */
public interface Hipertext
{
	/**
	 * Устанавливает текст для обработки.
	 *
	 * @param text Текст для обработки.
	 */
	void setText(@NotNull String text);

	/**
	 * Возвращает обработанный текст.
	 *
	 * @return Обработанный текст.
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
	 * @param tagType Тип HTML-тега.
	 */
	void deleteTag(@NotNull Tag tag, @NotNull TagType tagType);

	/**
	 * Удаляет все HTML-теги.
	 */
	void deleteTags();

	/**
	 * Удаляет все HTML-теги.
	 *
	 * @param tagType Тип HTML-тега.
	 */
	void deleteTags(@NotNull TagType tagType);

	/**
	 * Удаляет все HTML-теги.
	 *
	 * @param excludeTags Коллекция HTML-тегов которые не будут удалены.
	 */
	void deleteTags(@NotNull List<@NotNull Tag> excludeTags);

	/**
	 * Удаляет все HTML-теги.
	 *
	 * @param tagType Тип HTML-тега.
	 * @param excludeTags Коллекция HTML-тегов которые не будут удалены.
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
	 * @param entityType Тип HTML-сущностей.
	 */
	void deleteEntity(@NotNull Entity entity, @NotNull EntityType entityType);

	/**
	 * Удаляет все HTML-сущности.
	 */
	void deleteEntities();

	/**
	 * Удаляет все HTML-сущности.
	 *
	 * @param excludeEntities Коллекция HTML-сущностей которые не будут удалены.
	 */
	void deleteEntities(@NotNull List<@NotNull Entity> excludeEntities);

	/**
	 * Преобразует специальный символ на соответствующую HTML-сущность.
	 *
	 * @param entity Символ.
	 * @param entityType Тип HTML-сущностей.
	 */
	void encodeEntity(@NotNull Entity entity, @NotNull EntityType entityType);

	/**
	 * Преобразует все специальные символы на соответствующие HTML-сущности.
	 *
	 * @param entityType Тип HTML-сущностей.
	 */
	void encodeEntities(@NotNull EntityType entityType);

	/**
	 * Преобразует все специальные символы на соответствующие HTML-сущности.
	 *
	 * @param entityType Тип HTML-сущностей.
	 * @param excludeEntities Коллекция символов которые не будут преобразованы.
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
	 * @param entityType Тип HTML-сущностей.
	 */
	void decodeEntity(@NotNull Entity entity, @NotNull EntityType entityType);

	/**
	 * Преобразует все HTML-сущности в соответствующие специальные символы.
	 */
	void decodeEntities();

	/**
	 * Преобразует все HTML-сущности в соответствующие специальные символы.
	 *
	 * @param entityType Тип HTML-сущностей.
	 */
	void decodeEntities(@NotNull EntityType entityType);

	/**
	 * Преобразует все HTML-сущности в соответствующие специальные символы.
	 *
	 * @param excludeEntities Коллекция HTML-сущностей которые не будут преобразованы.
	 */
	void decodeEntities(@NotNull List<@NotNull Entity> excludeEntities);

	/**
	 * Преобразует все HTML-сущности в соответствующие специальные символы.
	 *
	 * @param excludeEntities Коллекция HTML-сущностей которые не будут преобразованы.
	 * @param entityType Тип HTML-сущностей.
	 */
	void decodeEntities(@NotNull EntityType entityType, @NotNull List<@NotNull Entity> excludeEntities);
}
