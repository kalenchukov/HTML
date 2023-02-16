# Журнал изменений
## vX.X.X - XX.XX.XXXX
### Добавлено
* Добавлен метод Hypertext.isEntity().
* Добавлен метод Hypertext.isTag().
* Добавлен метод Hypertext.findEntity().
* Добавлен метод Hypertext.findTags().

### Изменено
* Переименован интерфейс Hipertext -> Hypertext

## v1.2.0 - 20.01.2023
### Добавлено
* Добавлен AUTHOR.MD.
* Добавлен INSTALL.MD.
* Добавлен CHANGELOG.MD.
* Добавлено перечисление Regexp.
* Добавлен метод Hypertext.isCData().
* Добавлен метод Hypertext.isComment().
* Добавлен метод Hypertext.isDoctype().
* Добавлен метод Hypertext.isEndTag().
* Добавлен метод Hypertext.isEntityName().
* Добавлен метод Hypertext.isEntityNumeric().
* Добавлен метод Hypertext.isEntityUnicode().
* Добавлен метод Hypertext.isSelfClosingTag().
* Добавлен метод Hypertext.isStartTag().
* Добавлен метод Hypertext.findCData().
* Добавлен метод Hypertext.findComment().
* Добавлен метод Hypertext.findDoctype().
* Добавлен метод Hypertext.findEndTag().
* Добавлен метод Hypertext.findEntityName().
* Добавлен метод Hypertext.findEntityNumeric().
* Добавлен метод Hypertext.findEntityUnicode().
* Добавлен метод Hypertext.findSelfClosingTag().
* Добавлен метод Hypertext.findStartTag().

### Изменено
* Обновлён README.MD.
* Переименовано Regexp.START_TAG -> Regexp.OPEN_TAG.
* Переименовано Regexp.END_TAG -> Regexp.CLOSE_TAG.
* Переименовано TagType.START -> TagType.OPEN.
* Переименовано TagType.END -> TagType.CLOSE.
* Переименован метод Hypertext.isEndTag() -> Hypertext.isCloseTag().
* Переименован метод Hypertext.findEndTag() -> Hypertext.findCloseTag().
* Переименован метод Hypertext.isStartTag() -> Hypertext.isOpenTag().
* Переименован метод Hypertext.findStartTag() -> Hypertext.findOpenTag().

## v1.1.0 - 16.10.2022
### Добавлено
* Добавлен плагин maven-assembly в pom.xml.
* Добавлен плагин maven-compiler в pom.xml.
* Добавлен плагин maven-dependency в pom.xml.
* Добавлен плагин maven-javadoc в pom.xml.
* Добавлен плагин maven-source в pom.xml.

### Изменено
* Изменена версия требуемого модуля string-regexp.
* Изменён комментарий для Entity.Entity(Character, String, String, String).
* Изменён комментарий для Entity.getEntity(EntityType).
