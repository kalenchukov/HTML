# Журнал изменений
## v1.2.0 - 20.01.2023
### Добавлено
* Добавлен AUTHOR.MD.
* Добавлен INSTALL.MD.
* Добавлен CHANGELOG.MD.
* Добавлено перечисление Regexp.
* Добавлен метод Hipertext.isCData().
* Добавлен метод Hipertext.isComment().
* Добавлен метод Hipertext.isDoctype().
* Добавлен метод Hipertext.isEndTag().
* Добавлен метод Hipertext.isEntityName().
* Добавлен метод Hipertext.isEntityNumeric().
* Добавлен метод Hipertext.isEntityUnicode().
* Добавлен метод Hipertext.isSelfClosingTag().
* Добавлен метод Hipertext.isStartTag().
* Добавлен метод Hipertext.findCData().
* Добавлен метод Hipertext.findComment().
* Добавлен метод Hipertext.findDoctype().
* Добавлен метод Hipertext.findEndTag().
* Добавлен метод Hipertext.findEntityName().
* Добавлен метод Hipertext.findEntityNumeric().
* Добавлен метод Hipertext.findEntityUnicode().
* Добавлен метод Hipertext.findSelfClosingTag().
* Добавлен метод Hipertext.findStartTag().

### Изменено
* Обновлён README.MD.
* Переименовано Regexp.START_TAG -> Regexp.OPEN_TAG.
* Переименовано Regexp.END_TAG -> Regexp.CLOSE_TAG.
* Переименовано TagType.START -> TagType.OPEN.
* Переименовано TagType.END -> TagType.CLOSE.
* Переименован метод Hipertext.isEndTag() -> Hipertext.isCloseTag().
* Переименован метод Hipertext.findEndTag() -> Hipertext.findCloseTag().
* Переименован метод Hipertext.isStartTag() -> Hipertext.isOpenTag().
* Переименован метод Hipertext.findStartTag() -> Hipertext.findOpenTag().

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
