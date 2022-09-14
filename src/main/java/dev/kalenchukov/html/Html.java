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
import dev.kalenchukov.string.regexp.resources.Regexp;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс работы с HTML.
 */
public class Html implements Hipertext
{
	/**
	 * Текст для обработки.
	 */
	@NotNull
	private String text;

	/**
	 * Управляющие символы.
	 *
	 * <p>Символы которые используются в синтаксисе HTML-сущностей.</p>
	 */
	@NotNull
	private static final List<@NotNull Entity> CONTROL_SYMBOLS = List.of(
		Entity.NUM,
		Entity.AMP,
		Entity.SEMI
	);

	/**
	 * Конструктор для {@code Html}.
	 *
	 * @param text Текст для обработки.
	 */
	public Html(@NotNull final String text)
	{
		this.text = text;
	}

	/**
	 * @see Hipertext#setText(String)
	 */
	@Override
	public void setText(@NotNull final String text)
	{
		this.text = text;
	}

	/**
	 * @see Hipertext#getText()
	 */
	@NotNull
	@Override
	public String getText()
	{
		return this.text;
	}

	/**
	 * @see Hipertext#deleteTag(Tag)
	 */
	@Override
	public void deleteTag(@NotNull final Tag tag)
	{
		Arrays.stream(TagType.values())
			  .forEach(tagType -> this.deleteTag(tag, tagType));
	}

	/**
	 * @see Hipertext#deleteTag(Tag, TagType)
	 */
	@Override
	public void deleteTag(@NotNull final Tag tag, @NotNull final TagType tagType)
	{
		Map<TagType, Pattern> patterns = new HashMap<>();

		patterns.put(
			TagType.SELF_CLOSING,
			Pattern.compile(
				Regexp.HTML_SELF_CLOSING_TAG.getPattern().replace(
					"(?<name>[a-z]+)",
					"(?<name>" + tag.getName() + ")"
				),
				Pattern.CASE_INSENSITIVE + Pattern.DOTALL
			)
		);

		patterns.put(
			TagType.START,
			Pattern.compile(
				Regexp.HTML_START_TAG.getPattern().replace(
					"(?<name>[a-z]+)",
					"(?<name>" + tag.getName() + ")"
				),
				Pattern.CASE_INSENSITIVE + Pattern.DOTALL
			)
		);

		patterns.put(
			TagType.END,
			Pattern.compile(
				Regexp.HTML_END_TAG.getPattern().replace(
					"(?<name>[a-z]+)",
					"(?<name>" + tag.getName() + ")"
				),
				Pattern.CASE_INSENSITIVE + Pattern.DOTALL
			)
		);

		Matcher matcher = patterns.get(tagType)
								  .matcher(this.text);

		this.text = matcher.replaceAll("");
	}

	/**
	 * @see Hipertext#deleteTags()
	 */
	@Override
	public void deleteTags()
	{
		this.deleteTags(Collections.emptyList());
	}

	/**
	 * @see Hipertext#deleteTags(TagType)
	 */
	@Override
	public void deleteTags(@NotNull final TagType tagType)
	{
		Arrays.stream(Tag.values())
			  .forEach(tag -> this.deleteTag(tag, tagType));
	}

	/**
	 * @see Hipertext#deleteTags(List)
	 */
	@Override
	public void deleteTags(@NotNull final List<@NotNull Tag> excludeTags)
	{
		Arrays.stream(Tag.values())
			  .filter(tag -> !excludeTags.contains(tag))
			  .forEach(this::deleteTag);
	}

	/**
	 * @see Hipertext#deleteTags(TagType, List)
	 */
	@Override
	public void deleteTags(@NotNull final TagType tagType, @NotNull final List<@NotNull Tag> excludeTags)
	{
		Arrays.stream(Tag.values())
			  .filter(tag -> !excludeTags.contains(tag))
			  .forEach(tag -> this.deleteTag(tag, tagType));
	}

	/**
	 * @see Hipertext#deleteEntities()
	 */
	@Override
	public void deleteEntities()
	{
		this.deleteEntities(Collections.emptyList());
	}

	/**
	 * @see Hipertext#deleteEntities(List)
	 */
	@Override
	public void deleteEntities(@NotNull final List<@NotNull Entity> excludeEntities)
	{
		Arrays.stream(Entity.values())
			  .filter(entity -> !excludeEntities.contains(entity))
			  .forEach(this::deleteEntity);
	}

	/**
	 * @see Hipertext#deleteEntity(Entity)
	 */
	@Override
	public void deleteEntity(@NotNull final Entity entity)
	{
		Arrays.stream(EntityType.values())
			  .forEach(entityType -> this.deleteEntity(entity, entityType));
	}

	/**
	 * @see Hipertext#deleteEntity(Entity, EntityType)
	 */
	@Override
	public void deleteEntity(@NotNull final Entity entity, @NotNull EntityType entityType)
	{
		Map<EntityType, Pattern> patterns = new HashMap<>();

		patterns.put(
			EntityType.NAME,
			Pattern.compile(
				Regexp.HTML_ENTITY_NAME.getPattern().replace(
					"(?<name>[0-9A-Z]+)",
					"(?<name>" + entity.getEntity(entityType) + ")"
				)
			)
		);

		patterns.put(
			EntityType.NUMERIC,
			Pattern.compile(
				Regexp.HTML_ENTITY_NUMERIC.getPattern().replace(
					"(?<numericLeast>[0-9]+)",
					"(?<numericLeast>" + entity.getEntity(entityType) + ")"
				)
			)
		);

		patterns.put(
			EntityType.UNICODE,
			Pattern.compile(
				Regexp.HTML_ENTITY_UNICODE.getPattern().replace(
					"(?<unicodeLeast>[0-9A-F]+)",
					"(?<unicodeLeast>" + entity.getEntity(entityType) + ")"
				),
				Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
			)
		);

		Matcher matcher = patterns.get(entityType)
								  .matcher(this.text);

		this.text = matcher.replaceAll("");
	}

	/**
	 * @see Hipertext#deleteComments()
	 */
	@Override
	public void deleteComments()
	{
		Pattern pattern = Pattern.compile(
			Regexp.HTML_COMMENT.getPattern(),
			Pattern.CASE_INSENSITIVE + Pattern.DOTALL
		);

		Matcher matcher = pattern.matcher(this.text);

		this.text = matcher.replaceAll("");
	}

	/**
	 * @see Hipertext#deleteCData()
	 */
	@Override
	public void deleteCData()
	{
		Pattern pattern = Pattern.compile(
			Regexp.CDATA.getPattern(),
			Pattern.CASE_INSENSITIVE + Pattern.DOTALL
		);

		Matcher matcher = pattern.matcher(this.text);

		this.text = matcher.replaceAll("");
	}

	/**
	 * @see Hipertext#deleteDoctype()
	 */
	@Override
	public void deleteDoctype()
	{
		Pattern pattern = Pattern.compile(
			Regexp.HTML_DOCTYPE.getPattern(),
			Pattern.CASE_INSENSITIVE + Pattern.DOTALL
		);

		Matcher matcher = pattern.matcher(this.text);

		this.text = matcher.replaceAll("");
	}

	/**
	 * @see Hipertext#encodeEntity(Entity, EntityType)
	 */
	@Override
	public void encodeEntity(@NotNull final Entity entity, @NotNull final EntityType entityType)
	{
		this.text = this.text.replaceAll(
			Pattern.quote(entity.getSymbol().toString()),
			entity.getMnemonic(entityType)
		);
	}

	/**
	 * @see Hipertext#encodeEntities(EntityType)
	 */
	public void encodeEntities(@NotNull final EntityType entityType)
	{
		this.encodeEntities(entityType, Collections.emptyList());
	}

	/**
	 * @see Hipertext#encodeEntities(EntityType, List)
	 */
	public void encodeEntities(@NotNull final EntityType entityType,
							   @NotNull final List<@NotNull Entity> excludeEntities)
	{
		// В первую очередь, кодируются управляющие символы.
		CONTROL_SYMBOLS.stream()
			  .filter(controlSymbol -> !excludeEntities.contains(controlSymbol))
			  .forEach(controlSymbol -> this.encodeEntity(controlSymbol, entityType));

		Arrays.stream(Entity.values())
			  .filter(entity -> !CONTROL_SYMBOLS.contains(entity))
			  .filter(entity -> !excludeEntities.contains(entity))
			  .forEach(entity -> this.encodeEntity(entity, entityType));
	}

	/**
	 * @see Hipertext#decodeEntity(Entity)
	 */
	@Override
	public void decodeEntity(@NotNull final Entity entity)
	{
		Arrays.stream(EntityType.values())
			  .forEach(entityType -> this.decodeEntity(entity, entityType));
	}

	public void decodeEntity(@NotNull final Entity entity, @NotNull final EntityType entityType)
	{
		Map<EntityType, Pattern> patterns = new HashMap<>();

		patterns.put(
			EntityType.NAME,
			Pattern.compile(
				Regexp.HTML_ENTITY_NAME.getPattern().replace(
					"(?<name>[0-9A-Z]+)",
					"(?<name>" + entity.getEntity(entityType) + ")"
				)
			)
		);

		patterns.put(
			EntityType.NUMERIC,
			Pattern.compile(
				Regexp.HTML_ENTITY_NUMERIC.getPattern().replace(
					"(?<numericLeast>[0-9]+)",
					"(?<numericLeast>" + entity.getEntity(entityType) + ")"
				)
			)
		);

		patterns.put(
			EntityType.UNICODE,
			Pattern.compile(
				Regexp.HTML_ENTITY_UNICODE.getPattern().replace(
					"(?<unicodeLeast>[0-9A-F]+)",
					"(?<unicodeLeast>" + entity.getEntity(entityType) + ")"
				),
				Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
			)
		);

		Matcher matcher = patterns.get(entityType)
								  .matcher(this.text);

		this.text = matcher.replaceAll(entity.getSymbol().toString());
	}

	/**
	 * @see Hipertext#decodeEntities()
	 */
	@Override
	public void decodeEntities()
	{
		this.decodeEntities(Collections.emptyList());
	}

	/**
	 * @see Hipertext#decodeEntities(EntityType)
	 */
	@Override
	public void decodeEntities(@NotNull final EntityType entityType)
	{
		Arrays.stream(Entity.values())
			  .forEach(entity -> this.decodeEntity(entity, entityType));
	}

	/**
	 * @see Hipertext#decodeEntities(List)
	 */
	@Override
	public void decodeEntities(@NotNull final List<@NotNull Entity> excludeEntities)
	{
		Arrays.stream(Entity.values())
			  .filter(entity -> !excludeEntities.contains(entity))
			  .forEach(this::decodeEntity);
	}

	/**
	 * @see Hipertext#decodeEntities(EntityType, List)
	 */
	@Override
	public void decodeEntities(@NotNull final EntityType entityType,
							   @NotNull final List<@NotNull Entity> excludeEntities)
	{
		Arrays.stream(Entity.values())
			  .filter(entity -> !excludeEntities.contains(entity))
			  .forEach(entity -> this.decodeEntity(entity, entityType));
	}
}
