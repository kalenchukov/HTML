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

import dev.kalenchukov.html.resources.Entity;
import dev.kalenchukov.html.resources.EntityType;
import dev.kalenchukov.html.resources.Tag;
import dev.kalenchukov.html.resources.TagType;
import dev.kalenchukov.html.resources.Regexp;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс работы с HTML.
 */
public class Html implements Hypertext
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
	 * @param text текст для обработки.
	 */
	public Html(@NotNull final String text)
	{
		this.text = text;
	}

	/**
	 * @see Hypertext#setText(String)
	 */
	@Override
	public void setText(@NotNull final String text)
	{
		this.text = text;
	}

	/**
	 * @see Hypertext#getText()
	 */
	@NotNull
	@Override
	public String getText()
	{
		return this.text;
	}

	/**
	 * @see Hypertext#deleteTag(Tag)
	 */
	@Override
	public void deleteTag(@NotNull final Tag tag)
	{
		Arrays.stream(TagType.values())
			  .forEach(tagType -> this.deleteTag(tag, tagType));
	}

	/**
	 * @see Hypertext#deleteTag(Tag, TagType)
	 */
	@Override
	public void deleteTag(@NotNull final Tag tag, @NotNull final TagType tagType)
	{
		Map<TagType, Pattern> patterns = new HashMap<>();

		patterns.put(
			TagType.SELF_CLOSING,
			Pattern.compile(
				Regexp.SELF_CLOSING_TAG.getPattern().replace(
					"(?<name>[a-z]+)",
					"(?<name>" + tag.getName() + ")"
				),
				Pattern.CASE_INSENSITIVE + Pattern.DOTALL
			)
		);

		patterns.put(
			TagType.OPEN,
			Pattern.compile(
				Regexp.OPEN_TAG.getPattern().replace(
					"(?<name>[a-z]+)",
					"(?<name>" + tag.getName() + ")"
				),
				Pattern.CASE_INSENSITIVE + Pattern.DOTALL
			)
		);

		patterns.put(
			TagType.CLOSE,
			Pattern.compile(
				Regexp.CLOSE_TAG.getPattern().replace(
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
	 * @see Hypertext#deleteTags()
	 */
	@Override
	public void deleteTags()
	{
		this.deleteTags(Collections.emptyList());
	}

	/**
	 * @see Hypertext#deleteTags(TagType)
	 */
	@Override
	public void deleteTags(@NotNull final TagType tagType)
	{
		Arrays.stream(Tag.values())
			  .forEach(tag -> this.deleteTag(tag, tagType));
	}

	/**
	 * @see Hypertext#deleteTags(List)
	 */
	@Override
	public void deleteTags(@NotNull final List<@NotNull Tag> excludeTags)
	{
		Arrays.stream(Tag.values())
			  .filter(tag -> !excludeTags.contains(tag))
			  .forEach(this::deleteTag);
	}

	/**
	 * @see Hypertext#deleteTags(TagType, List)
	 */
	@Override
	public void deleteTags(@NotNull final TagType tagType, @NotNull final List<@NotNull Tag> excludeTags)
	{
		Arrays.stream(Tag.values())
			  .filter(tag -> !excludeTags.contains(tag))
			  .forEach(tag -> this.deleteTag(tag, tagType));
	}

	/**
	 * @see Hypertext#deleteEntities()
	 */
	@Override
	public void deleteEntities()
	{
		this.deleteEntities(Collections.emptyList());
	}

	/**
	 * @see Hypertext#deleteEntities(List)
	 */
	@Override
	public void deleteEntities(@NotNull final List<@NotNull Entity> excludeEntities)
	{
		Arrays.stream(Entity.values())
			  .filter(entity -> !excludeEntities.contains(entity))
			  .forEach(this::deleteEntity);
	}

	/**
	 * @see Hypertext#deleteEntity(Entity)
	 */
	@Override
	public void deleteEntity(@NotNull final Entity entity)
	{
		Arrays.stream(EntityType.values())
			  .forEach(entityType -> this.deleteEntity(entity, entityType));
	}

	/**
	 * @see Hypertext#deleteEntity(Entity, EntityType)
	 */
	@Override
	public void deleteEntity(@NotNull final Entity entity, @NotNull EntityType entityType)
	{
		Map<EntityType, Pattern> patterns = new HashMap<>();

		patterns.put(
			EntityType.NAME,
			Pattern.compile(
				Regexp.ENTITY_NAME.getPattern().replace(
					"(?<name>[0-9A-Z]+)",
					"(?<name>" + entity.getEntity(entityType) + ")"
				)
			)
		);

		patterns.put(
			EntityType.NUMERIC,
			Pattern.compile(
				Regexp.ENTITY_NUMERIC.getPattern().replace(
					"(?<numericLeast>[0-9]+)",
					"(?<numericLeast>" + entity.getEntity(entityType) + ")"
				)
			)
		);

		patterns.put(
			EntityType.UNICODE,
			Pattern.compile(
				Regexp.ENTITY_UNICODE.getPattern().replace(
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
	 * @see Hypertext#deleteComments()
	 */
	@Override
	public void deleteComments()
	{
		Pattern pattern = Pattern.compile(
			Regexp.COMMENT.getPattern(),
			Pattern.CASE_INSENSITIVE + Pattern.DOTALL
		);

		Matcher matcher = pattern.matcher(this.text);

		this.text = matcher.replaceAll("");
	}

	/**
	 * @see Hypertext#deleteCData()
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
	 * @see Hypertext#deleteDoctype()
	 */
	@Override
	public void deleteDoctype()
	{
		Pattern pattern = Pattern.compile(
			Regexp.DOCTYPE.getPattern(),
			Pattern.CASE_INSENSITIVE + Pattern.DOTALL
		);

		Matcher matcher = pattern.matcher(this.text);

		this.text = matcher.replaceAll("");
	}

	/**
	 * @see Hypertext#encodeEntity(Entity, EntityType)
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
	 * @see Hypertext#encodeEntities(EntityType)
	 */
	@Override
	public void encodeEntities(@NotNull final EntityType entityType)
	{
		this.encodeEntities(entityType, Collections.emptyList());
	}

	/**
	 * @see Hypertext#encodeEntities(EntityType, List)
	 */
	@Override
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
	 * @see Hypertext#decodeEntity(Entity)
	 */
	@Override
	public void decodeEntity(@NotNull final Entity entity)
	{
		Arrays.stream(EntityType.values())
			  .forEach(entityType -> this.decodeEntity(entity, entityType));
	}

	/**
	 * @see Hypertext#decodeEntity(Entity, EntityType)
	 */
	@Override
	public void decodeEntity(@NotNull final Entity entity, @NotNull final EntityType entityType)
	{
		Map<EntityType, Pattern> patterns = new HashMap<>();

		patterns.put(
			EntityType.NAME,
			Pattern.compile(
				Regexp.ENTITY_NAME.getPattern().replace(
					"(?<name>[0-9A-Z]+)",
					"(?<name>" + entity.getEntity(entityType) + ")"
				)
			)
		);

		patterns.put(
			EntityType.NUMERIC,
			Pattern.compile(
				Regexp.ENTITY_NUMERIC.getPattern().replace(
					"(?<numericLeast>[0-9]+)",
					"(?<numericLeast>" + entity.getEntity(entityType) + ")"
				)
			)
		);

		patterns.put(
			EntityType.UNICODE,
			Pattern.compile(
				Regexp.ENTITY_UNICODE.getPattern().replace(
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
	 * @see Hypertext#decodeEntities()
	 */
	@Override
	public void decodeEntities()
	{
		this.decodeEntities(Collections.emptyList());
	}

	/**
	 * @see Hypertext#decodeEntities(EntityType)
	 */
	@Override
	public void decodeEntities(@NotNull final EntityType entityType)
	{
		Arrays.stream(Entity.values())
			  .forEach(entity -> this.decodeEntity(entity, entityType));
	}

	/**
	 * @see Hypertext#decodeEntities(List)
	 */
	@Override
	public void decodeEntities(@NotNull final List<@NotNull Entity> excludeEntities)
	{
		Arrays.stream(Entity.values())
			  .filter(entity -> !excludeEntities.contains(entity))
			  .forEach(this::decodeEntity);
	}

	/**
	 * @see Hypertext#decodeEntities(EntityType, List)
	 */
	@Override
	public void decodeEntities(@NotNull final EntityType entityType,
							   @NotNull final List<@NotNull Entity> excludeEntities)
	{
		Arrays.stream(Entity.values())
			  .filter(entity -> !excludeEntities.contains(entity))
			  .forEach(entity -> this.decodeEntity(entity, entityType));
	}

	/**
	 * @see Hypertext#isComment()
	 */
	@Override
	public boolean isComment()
	{
		return this.is(
			Regexp.COMMENT,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE + Pattern.DOTALL
		);
	}

	/**
	 * @see Hypertext#isEntity()
	 */
	@Override
	public boolean isEntity()
	{
		return this.isEntityName() || this.isEntityUnicode() || this.isEntityNumeric();
	}

	/**
	 * @see Hypertext#isEntityName()
	 */
	@Override
	public boolean isEntityName()
	{
		return this.is(
			Regexp.ENTITY_NAME,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
	}

	/**
	 * @see Hypertext#isEntityNumeric()
	 */
	@Override
	public boolean isEntityNumeric()
	{
		return this.is(Regexp.ENTITY_NUMERIC);
	}

	/**
	 * @see Hypertext#isEntityUnicode()
	 */
	@Override
	public boolean isEntityUnicode()
	{
		return this.is(
			Regexp.ENTITY_UNICODE,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
	}

	/**
	 * @see Hypertext#isDoctype()
	 */
	@Override
	public boolean isDoctype()
	{
		return this.is(
			Regexp.DOCTYPE,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE + Pattern.DOTALL
		);
	}

	/**
	 * @see Hypertext#isCData()
	 */
	@Override
	public boolean isCData()
	{
		return this.is(
			Regexp.CDATA,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE + Pattern.DOTALL
		);
	}

	/**
	 * @see Hypertext#isCloseTag()
	 */
	@Override
	public boolean isCloseTag()
	{
		return this.is(
			Regexp.CLOSE_TAG,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
	}

	/**
	 * @see Hypertext#isTag()
	 */
	@Override
	public boolean isTag()
	{
		return this.isOpenTag() || this.isCloseTag() || this.isSelfClosingTag();
	}

	/**
	 * @see Hypertext#isOpenTag()
	 */
	@Override
	public boolean isOpenTag()
	{
		return this.is(
			Regexp.OPEN_TAG,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE + Pattern.DOTALL
		);
	}

	/**
	 * @see Hypertext#isSelfClosingTag()
	 */
	@Override
	public boolean isSelfClosingTag()
	{
		return this.is(
			Regexp.SELF_CLOSING_TAG,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE + Pattern.DOTALL
		);
	}

	/**
	 * @see Hypertext#findTags()
	 */
	@Override
	@Unmodifiable
	@NotNull
	public List<@NotNull String> findTags()
	{
		final List<String> tags = new ArrayList<>();
		tags.addAll(this.findOpenTags());
		tags.addAll(this.findSelfClosingTag());
		tags.addAll(this.findCloseTag());

		return Collections.unmodifiableList(tags);
	}

	/**
	 * @see Hypertext#findSelfClosingTag()
	 */
	@Override
	@Unmodifiable
	@NotNull
	public List<@NotNull String> findSelfClosingTag()
	{
		return this.find(
			Regexp.SELF_CLOSING_TAG,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE + Pattern.DOTALL
		);
	}

	/**
	 * @see Hypertext#findOpenTags()
	 */
	@Override
	@Unmodifiable
	@NotNull
	public List<@NotNull String> findOpenTags()
	{
		return this.find(
			Regexp.OPEN_TAG,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE + Pattern.DOTALL
		);
	}

	/**
	 * @see Hypertext#findCloseTag()
	 */
	@Override
	@Unmodifiable
	@NotNull
	public List<@NotNull String> findCloseTag()
	{
		return this.find(
			Regexp.CLOSE_TAG,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
	}

	/**
	 * @see Hypertext#findCData()
	 */
	@Override
	@Unmodifiable
	@NotNull
	public List<@NotNull String> findCData()
	{
		return this.find(
			Regexp.CDATA,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE + Pattern.DOTALL
		);
	}

	/**
	 * @see Hypertext#findDoctype()
	 */
	@Override
	@Unmodifiable
	@NotNull
	public List<@NotNull String> findDoctype()
	{
		return this.find(
			Regexp.DOCTYPE,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE + Pattern.DOTALL
		);
	}

	/**
	 * @see Hypertext#findEntity()
	 */
	@Override
	@Unmodifiable
	@NotNull
	public List<@NotNull String> findEntity()
	{
		final List<String> entities = new ArrayList<>();
		entities.addAll(this.findEntityName());
		entities.addAll(this.findEntityNumeric());
		entities.addAll(this.findEntityUnicode());

		return Collections.unmodifiableList(entities);
	}

	/**
	 * @see Hypertext#findEntityName()
	 */
	@Override
	@Unmodifiable
	@NotNull
	public List<@NotNull String> findEntityName()
	{
		return this.find(
			Regexp.ENTITY_NAME,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
	}

	/**
	 * @see Hypertext#findEntityNumeric()
	 */
	@Override
	@Unmodifiable
	@NotNull
	public List<@NotNull String> findEntityNumeric()
	{
		return this.find(Regexp.ENTITY_NUMERIC);
	}

	/**
	 * @see Hypertext#findEntityUnicode()
	 */
	@Override
	@Unmodifiable
	@NotNull
	public List<@NotNull String> findEntityUnicode()
	{
		return this.find(
			Regexp.ENTITY_UNICODE,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
	}

	/**
	 * @see Hypertext#findComment()
	 */
	@Override
	@Unmodifiable
	@NotNull
	public List<@NotNull String> findComment()
	{
		return this.find(
			Regexp.COMMENT,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE + Pattern.DOTALL
		);
	}

	/**
	 * Проверяет, является ли строка значением.
	 *
	 * @param regexp регулярное выражение.
	 * @return {@code true}, если строка является значением, иначе {@code false}.
	 */
	private boolean is(@NotNull final Regexp regexp)
	{
		return this.is(regexp, 0);
	}

	/**
	 * Проверяет, является ли строка значением.
	 *
	 * @param regexp регулярное выражение.
	 * @param flags флаги регулярного выражения.
	 * @return {@code true}, если строка является значением, иначе {@code false}.
	 */
	private boolean is(@NotNull final Regexp regexp, @NotNull final Integer flags)
	{
		final Pattern pattern = Pattern.compile(regexp.getPattern(), flags);
		final Matcher matcher = pattern.matcher(this.getText());

		return matcher.matches();
	}

	/**
	 * Выполняет поиск значений.
	 *
	 * @return коллекцию с найденными значениями.
	 */
	@Unmodifiable
	@NotNull
	private List<@NotNull String> find(@NotNull final Regexp regexp)
	{
		return this.find(regexp, 0);
	}

	/**
	 * Выполняет поиск значений.
	 *
	 * @param flags флаги регулярного выражения.
	 * @return коллекцию с найденными значениями.
	 */
	@Unmodifiable
	@NotNull
	private List<@NotNull String> find(@NotNull final Regexp regexp, @NotNull final Integer flags)
	{
		final List<String> values = new ArrayList<>();

		final Pattern pattern = Pattern.compile(regexp.getPattern(), flags);
		final Matcher matcher = pattern.matcher(this.getText());

		while (matcher.find()) {
			values.add(matcher.group(regexp.getGroup()));
		}

		return Collections.unmodifiableList(values);
	}
}
