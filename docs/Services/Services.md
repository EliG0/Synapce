```text
╭─╮╭─╴╭─╮╷ ╷╷╭─╴╭─╴╭─╮
╰─╮├╴ ├┬╯│╭╯││  ├╴ ╰─╮
╰─╯╰─╴╵╰╴╰╯ ╵╰─╴╰─╴╰─╯
```

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

## Xarxes

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

### AuthService

Сервис авторизации, аутентификации и регистрации пользователей. Работает в связке с PasswordUtils (BCrypt) (
см [Utils](../Utils/Utils.md)).

* `OperationResult<User> authorisation(String login, String password)` — проверка учетных данных и вход в систему.
* `OperationResult<Void> registration(User user, String password)` — валидация и создание нового аккаунта с хэшированием
  пароля.

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

### NavigationService

Сервис для управления экранами и переключения контекстов внутри Single Window интерфейса.

> Будет реализован в процессе построения JavaFX UI.
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

### ReportService

Сервис генерации аналитических отчетов в формате PDF.

> Формальное требование вуза для курсовой. Будет реализован перед защитой.
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

### SessionService

Сервис-синглтон для отслеживания состояния текущей активной сессии в приложении..

* `private User currentUser()` — объект текущего авторизованного пользователя.
* `private User getCurrentUser()` — получение данных текущей сессии (тема, роль, настройки).
* `OperationResult<Void> login(User user)` — фиксация пользователя в системе при успешной авторизации.
* `OperationResult<Void> logout()` — сброс текущей сессии и возврат на экран логина.

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

### UserService

Прямой CRUD-сервис для управления учетными записями в базе данных. Взаимодействует с UserRepository (
см. [Repositories](../Repositories/Repositories.md)).

* `OperationResult<Void> addUser(User user)` — добавление пользователя.
* `OperationResult<Void> deleteUser(int id)` — удаление пользователя по ID.
* `OperationResult<User> getUser(int userId)` — поиск пользователя по ID.
* `OperationResult<User> getUser(String username)` — поиск пользователя по логину для авторизации.
* `OperationResult<Void> updateProfile(User user)` — обновление изменяемых данных (смена пароля, выбор темы оформления).

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

## Hermaeus

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

### GameService

Сервис для взаимодействия с установленной на компьютере игрой. Отвечает за проверку путей, чтение игровых архивов и
безопасный запуск.

* `OperationResult<Void> launchGame()` — запуск игры с подключенными модификациями. Блокирует интерфейс Заркса или
  уходит в фоновый режим отслеживания процесса.
* `OperationResult<Boolean> isGameRunning()` — проверка, запущен ли процесс игры в данный момент (чтобы заблокировать
  изменение модов «на лету»).
* `OperationResult<String> getGameVersion()` — автоматическое определение версии игры по указанному пути для проверки
  совместимости с модами.

> см. [GameService.java](../../src/main/java/ru/lgtu/xarxes/hermaeus/service/GameService.java)
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

### ModService

Сервис для работы с глобальным каталогом модификаций (интеграция с API NexusMods / удаленным сервером) и скачивания
файлов.

* `OperationResult<Mod> getRemoteModDetails(int remoteId)` — загрузка полной карточки мода из интернета (описание,
  скриншоты, версия, зависимости).
* `OperationResult<Void> downloadMod(int remoteId, DownloadProgressListener listener)` — запуск асинхронного скачивания
  архива мода с диска сервера в локальный кэш Заркса с передачей прогресса в UI.

> см. [ModService.java](../../src/main/java/ru/lgtu/xarxes/hermaeus/service/ModService.java)
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

### SearchService

Высокоуровневый поисковый агрегатор. Координирует запросы между локальным хранилищем и внешними репозиториями,
обеспечивая сквозную фильтрацию.

* `OperationResult<SearchResult> globalSearch(SearchQuery query)` — сквозной поиск по всей системе. Принимает сложный
  объект запроса (строка, теги, фильтры), опрашивает локальные и внешние сервисы и возвращает объединенный результат.
* `OperationResult<List<String>> getSearchSuggestions(String partialQuery)` — генерация подсказок «на лету» при вводе
  текста в поисковую строку GUI (интеллектуальный автокомплит).
* `OperationResult<List<ModTag>> getAvailableTags()` — получение списка всех доступных тегов и категорий для настройки
  расширенного поиска в интерфейсе.
*

> см. [SearchService.java](../../src/main/java/ru/lgtu/xarxes/hermaeus/service/SearchService.java)
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

## Oghma

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

### InstalledModService

Прямой CRUD-сервис для управления скачанными модами.
Взаимодействуетс [InstalledModRepository](../Repositories/Repositories.md#installedmodrepository).

* `OperationResult<Void> installMod(InstalledMod mod)` — регистрация нового скачанного мода в локальной базе данных
  Заркса и его первичная инициализация.
* `OperationResult<Void> deleteMod(int modId)` — полное удаление модификации из системы (очистка записей в БД и
  физическое удаление файлов с диска).
* `OperationResult<InstalledMod> getMod(int modId)` — получение детальной информации о конкретном моде по его
  уникальному ID.
* `OperationResult<List<InstalledMod>> getAllMods()` — получение полного списка всех установленных модов для отображения
  в главной таблице GUI.
* `OperationResult<Void> toggleModStatus(int modId, ModStatus status)` — быстрое переключение состояния мода (например:
  Активен / Отключен / Требует обновления).
* `OperationResult<Void> changeLoadOrder(int userId, List<InstalledMod> reorderedMods)` — сохранить измененный
  пользователем порядок загрузки модов.

> см. [InstalledModService.java](../../src/main/java/ru/lgtu/xarxes/oghma/service/InstalledModService.java)

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
