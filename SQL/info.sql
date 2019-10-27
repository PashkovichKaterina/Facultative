/*insert into facultative.roles(title) values ('Администратор'), ('Преподаватель'), ('Студент');

insert into facultative.levels(title_ru, title_en) values 
('Пользователь', 'User'), (
'Уверенный пользователь', 'Confident user'),  
('Продвинутый пользователь', 'Advanced user'), 
('Профессиональный уровень', 'Professional level');

insert into facultative.skills(title_ru, title_en) values 
('Photoshop', 'Photoshop'), 
('Illustrator', 'Illustrator'), 
('3ds Max', '3ds Max'), 
('Maya', 'Maya'), 
('Figma', 'Figma'), 
('Blender', 'Blender'), 
('Unity', 'Unity');

insert into facultative.categories(title_ru, title_en) values 
('2D графика', '2D graphics'), 
('3D графика', '3D graphics'), 
('Разработка игр', 'Game development'), 
('Разработка сайтов', 'Website development');

insert into facultative.days(title_ru, title_en) values 
('Понедельник', 'Monday'),
('Вторник', 'Tuesday'), 
('Среда', 'Wednesday'), 
('Четверг', 'Thursday'), 
('Пятница', 'Friday'), 
('Суббота', 'Saturday');

insert into facultative.positions(title_ru, title_en) values 
('2D художник', '2D artist'), 
('3D художник', '3D artist'), 
('UI/UX дизайнер', 'UI / UX designer'), 
('Разработчик игр на Unity', 'Unity game developer'), 
('Разработчик анимаций', 'Animation developer');

insert into facultative.statuses(title_ru, title_en) values 
('Заявка подана', 'Application filed'), 
('Зачислен на курсы', 'Enrolled in course'), 
('Обучается', 'Studying');

insert into facultative.users(login, email, role_id, password) values
('admin', 'administration@gmail.com', 1, '1fbced89af553b7073a11c15a7c786c6d8b88c7e54f761a585ad56b8f16f9297a88d18945a26628e0f4dc50d6eb1a71fd3e5ba2309623c8510f0bb1125db4f689798b43cba85b746c67d846cdcf2cbc4'),
('tarashkevich', 'tarashkevich@gmail.com', 2, 'd64b44dc2395ba6dad3e9a9d5b42e506bfecb6118ea7107521a427bab2198f5f9bcee40ae25c180f9ecfbeeda17d2d33351879e5c792fd1bd010587fe105936b67e8438296b687f78ef2bc0783cf639c'),
('grishkevich', 'grishkevich@gmail.com', 2, 'da46c833fe553769c4b595d587e08d7c805696b62001cd5ede65939c70f83cdffdc2779b0bf76a42050785dd14383a0c97a03efa05edd952252023fd8ea0a997b0cc1f62b4351c66951eccf6fe53e601'),
('vanislavchick', 'vanislavchick@gmail.com', 2, '4af2a0567c11296cf0c8d06af9e264c36707ae3152fad0ad3fac25b80637004f0f2f72f66a5e56541be12378997777bc672413aaf1c410b8e31e1f065e3efc4de945bbafa25259a4184e03228ad2275c'),
('sablevsky', 'sablevsky@gmail.com', 2, 'f24687815261828bc7332786cd57088f46128aaa596f012b49f35b35250402f1025cd85be23319afb53430999438a688c7e72e5a880c8a67473a6976bf6b0ffe83bf86db0fb0b5f56aa5c99ff55e3876'),
('akimova', 'akimova@gmail.com', 2, 'beb3e28c3fb55b669a1b7ab853a824d7409bbc998c0e4011716c85d529eda411d09c5ed9383ceb268ba29fd9fb8495aa10626be122880ecec5bfdce0532d30913317133049f2047b9fc797cdd2d2ae97'),
('kulbi', 'kulbi@gmail.com', 2, '3c3fe24d1d7037502a431955e32f6001d2285c052a68b8adbc0533208d085c627a729c784270a5b26a07adc1777a55c529222267e601a188cf2a9a4260e395e5173ef2b78e41cb12d5c2d37e784541d9'),
('fetrov', 'fetrov@gmail.com', 2, 'f1bee837e958d074e66dcd2495b7e5ea09969f0f4d76b5ac159a03a360592575accd21cadc81d81b5df187597ca40ccc5aa9e27f9493d6a3d89ffc7c537c703da9ed630cf645c01702d08bc2b5897ed4'),
('krivulko', 'krivulko@gmail.com', 3, 'd120601cb64b6e886f552b1e3af554eb48976cf3b55d4bfc844afc91699f99db2d050b727b3e0b4fb7045f68dddb3031ed6c504151c57a9f20a8fc2643b18408b9391e4580e04a267ec75c68bbc3f25c'),
('nalivko', 'nalivko@gmail.com', 3, '6b8be5e6c3211f357884cb686efce2956f95a998a4186aee3574be7f089d365a3594e43f4addcf5effac69ce002ecc05025d78c1e877944d29078a944b24f135a2dceaaadb8caacdd2ebed60660f6020'),
('evseeva', 'evseeva@gmail.com', 3, 'cd3956c9ac1723d3a80c44eced6cce7e029008512efd5bf4171e9655b569ecaf18682cbe2db480e9262a011347ad28df6c5153654a49d35d965790217672112bf9b987fb61b058e5b311c6ad514f7c97'),
('zinkov', 'zinkov@gmail.com', 3, '11afac865d1f42ed121dd05e3daae24fb75e7463917cbce8bca4434994af78feb8ccca00d75e833a93107d0ad912a24f448eb050f940034cc147826a38c0c8be7258c3c8521fa320d3636335a6f7efce'),
('dragon', 'dragon@gmail.com', 3, '7ee3ed43891f4f541a126a3ebb58007ae524a94fca29e76b86a37926c6ffaeb6b449f28331ec1855075e6049929a4fd12243ea8ec15c160902c2e24d251ab03e27b21748b2ef6045464864dab3e259ca'),
('komissarova', 'komissarova@gmail.com', 3, '553d6b9c5638e24d306111ae63ceb6ce5ac872ed02c1ca381ee767e3bd1e47efa69501353f4559138f307173cabbf3935a1fa5df612e85e79bb16168975fbcc23e7d015e2f2673ebe18c491c028a2c7d'),
('kravchenko', 'kravchenko@gmail.com', 3, '5b3c33667ca1fec6871768bdee56caaced251a47328abf6f4516d2962871f3a95ef001699d204c077b7ce40785867e27139bac7fd505504db4b205d1b12ad8d8c7417ee86d4b2f1390a1e7cb6be1d02c'),
('korov', 'korov@gmail.com', 3, 'd3c0cb6907ced7ea180701bdac55d91fc59af61deadef7c3cc17d0815840e598c37c8df1fd3a5b5e52238b69665f8b06f3c3becf4fcad58c284493d8e1b77f1d982da83f7196f8cfb3f9c32d6352e366'),
('pashkovich', 'pashkovich@gmail.com', 3, '3f91bb514e237ae19af952b6bdc39822b2b94512aac443dec5d0ffebbe9af366e016a2615a408a993e133de94a0642e46a93e8b148a424df9e6cb4562f1ffb61c8b72c3ff654c0ab4d0c8c5a91df02cb'),
('petrov', 'petrov@gmail.com', 3, 'a865157cd03f9f5b21c3c803163f5e5007c96c8f733a444ede67e0ac33fed39347758f9ef02f7f210f4b7b64c24bd1d616d85074e908d3309cbe36c1e0f1e0675d778f897621f4e0b105eafa4482343c'),
('bakeeva', 'bakeeva@gmail.com', 3, 'da07657d99c96030b6cd2c95d13bcd425641f2e5018ec59d12b214e7933ab48b03e511a8f78c5f761c7e5370428d0ca93fd86f05a806a2450656620d3c340017da0d7a714864efa7552810df1562738d');

insert into facultative.user_details(user_id, surname, name, patronymic, position, phone) values
(2, 'Тарашкевич', 'Леонид', 'Аркадьевич', 2, '+375 (29) 568-97-23'),
(3, 'Гришкевич', 'Юлия', 'Александровна', 1, '+375 (29) 924-68-45'),
(4, 'Ваниславчик', 'Максим', 'Юрьевич', 4, '+375 (29) 345-47-17'),
(5, 'Саблевский', 'Федор', 'Степанович', 3, '+375 (29) 873-35-04'),
(6, 'Акимова', 'Татьяна', 'Федоровна', 3, '+375 (29) 578-37-67'),
(7, 'Кульбицкий', 'Алексей', 'Акимович', 5, '+375 (29) 247-35-85'),
(8, 'Фетров', 'Степан', 'Васильевич', 1, '+375 (29) 685-07-96');

insert into facultative.user_details(user_id, surname, name, patronymic, date_of_birth, phone) values
(9, 'Кривулько', 'Диана', 'Романовна', '1999-02-09', '+375 (29) 568-34-87'),
(10, 'Наливко', 'Юлия', 'Грегорьевна', '1998-12-09', '+375 (29) 863-85-46'),
(11, 'Евсеева', 'Галина', 'Степановна', '2001-09-24', '+375 (29) 435-09-42'),
(12, 'Зиньков', 'Федор', 'Петрович', '2000-02-12', '+375 (29) 572-13-53'),
(13, 'Драгонов', 'Петр', 'Васильевич', '2000-05-15', '+375 (29) 974-45-21'),
(14, 'Комиссарова', 'Яна', 'Дмитриевна', '1999-06-16', '+375 (29) 456-13-45'),
(15, 'Кравченко', 'Валентина', 'Кирилловная', '1999-10-10', '+375 (29) 653-78-65'),
(16, 'Коробов', 'Василий', 'Петрович', '1998-11-16', '+375 (29) 674-96-75'),
(17, 'Пашкович', 'Екатерина', 'Васильевна', '1999-10-10', '+375 (29) 653-78-65'),
(18, 'Петров', 'Евгений', 'Витальевич', '1999-12-10', '+375 (29) 667-73-65'),
(19, 'Бакеева', 'Евгения', 'Сергеевна', '1999-05-03', '+375 (29) 653-08-67');

insert into facultative.courses(title_ru, title_en, teacher_id, number_of_classes, category_id, availability, description_ru, description_en) values
('Концепт-арт персонажей', 'Character concept art', 3, 40, 1, true, 'Концепт-арт для тех, кому нравится придумывать и создавать. На занятиях студентам предстоит разрабатывать дизайн персонажей от простых людей до невероятных существ, используя навыки 2D художника. Будет изучена анатомия анатомии чеолвека и животных, работа с дизайном персонажа и его экипировки. Используемые программы на курсе: Adobe Photoshop, Sketchbook Pro, Alchemy, Pureref, PolyBrush.', 'Concept art for those who like to invent and create. In the classroom, students will have to develop character designs from ordinary people to incredible creatures using the skills of a 2D artist. We will study the anatomy of the anatomy of the human and animal, work with the design of the character and his equipment. Used programs on the course: Adobe Photoshop, Sketchbook Pro, Alchemy, Pureref, PolyBrush.'),
('Концепт-арт окружения', 'Environment concept art', 3, 40, 1, true, 'Этот курс для тех, кому нравится придумывать и создавать. На занятиях студентам предстоит разработать дизайн окружения, от мелких предметов до больших локаций, используя навыки 2D художника, а также изучить все яркие стили архитектуры и использовать их в создании своих уникальных сооружений и миров. Будет изучена архитектура и интерьер для игр и кино. Используемые программы на курсе: Adobe Photoshop, Sketchbook Pro, Alchemy, Pureref, PolyBrush.', 'This course is for those who like to invent and create. In the classroom, students will have to develop an environment design, from small objects to large locations, using the skills of a 2D artist, and also to study all the vibrant styles of architecture and use them in creating their own unique structures and worlds. Architecture and interior for games and cinema will be studied. Used programs on the course: Adobe Photoshop, Sketchbook Pro, Alchemy, Pureref, PolyBrush.'),
('Концепт-арт оружия', 'Weapon concept art', 3, 40, 1, true, 'Этот небольшой курс для тех, кто хочет больше углубиться в понимание строения различных видов оружия. Он даст вам понимание специфики дизайна оружия для игровой индустрии и все необходимые навыки создания рабочего концепт-арта. На занятиях студентам предстоит изучить историю и виды вооружения в различных сеттингах, а так же разработать собственный дизайн, используя навыки 2D художника. Используемые программы на курсе: Adobe Photoshop, Pureref, Keyshot.', 'This short course is for those who want to delve deeper into the structure of various types of weapons. It will give you an understanding of the specifics of weapon design for the gaming industry and all the necessary skills to create a working concept art. In the classroom, students will have to study the history and types of weapons in various settings, as well as develop their own design using the skills of a 2D artist. Used programs on the course: Adobe Photoshop, Pureref, Keyshot.'),
('Иллюстрация базовый уровень', 'Basic level illustration', 8, 35, 1, true, 'Этот курс для тех, кто хочет рисовать атмосферные и динамичные арты. Студентам предстоит заняться созданием коммерчески привлекательной иллюстрации для игр. Знания, приобретенные на этом курсе, помогут создать актуальное портфолио, которое поможет найти работу, а полезные советы и приёмы повысят Вашу эффективность. Вы изучите основные принципы стилизации и научитесь создавать иллюстрации для игр. Используемые программы на курсе: Adobe Photoshop, Pureref.', 'This course is for those who want to draw atmospheric and dynamic art. Students will have to create commercially attractive game illustrations. The knowledge acquired in this course will help to create an up-to-date portfolio that will help you find a job, and useful tips and techniques will increase your effectiveness. You will learn the basic principles of stylization and learn how to create illustrations for games. Used programs on the course: Adobe Photoshop, Pureref.'),
('Иллюстрация продвинутый уровень', 'Advanced level illustration', 8, 25, 1, true, 'Это курс для тех, кто уже имеет опыт работы с иллюстрацией и хочет прокачать свой уровень еще больше. Будем учиться создавать динамичные иллюстрации на профессиональном уровне, делать упор на композицию и развивать пространственное мышление. Будет рассмотрено изучение композиции и работа с сюжетом. Используемые программы на курсе: Adobe Photoshop, Pureref, SketchUp.', 'This course is for those who already have experience with illustration and want to pump their level even more. We will learn to create dynamic illustrations at a professional level, focus on composition and develop spatial thinking. The study of composition and work with the plot will be considered. Used programs on the course: Adobe Photoshop, Pureref, SketchUp.'),
('Рисование комиксов', 'Comic book', 8, 30, 1, true, 'На этом курсе студенты научатся рисовать свои комиксы по заданному сценарию, и пройдут весь путь от раскадровки до финальных цветных страниц. По завершении курса студенты получат все необходимые знания, развив которые они смогут профессионально работать художниками комиксов. Используемые программы на курсе: Adobe Photoshop, Pureref, Clip Studio Paint.', 'In this course, students will learn how to draw their comics in a given scenario, and will go all the way from storyboards to final color pages. Upon completion of the course, students will receive all the necessary knowledge, having developed which they can professionally work as comic book artists. Used programs on the course: Adobe Photoshop, Pureref, Clip Studio Paint.'),
('3D игровая анимация', '3D game animation', 7, 45, 2, true, 'Этот курс для тех, кто хочет освоить основные навыки и приемы, используемые для анимации трехмерных персонажей. В процессе обучения студент использует наши тренировочные модели, или создает свои собственные , собирает несколько видеороликов для портфолио, получает набор универсальных инструментов для оживления своих идей. Используемые программы на курсе: 3ds Max, Adobe Photoshop, Adobe After Effects, Unity 3D.', 'This course is for those who want to learn the basic skills and techniques used to animate three-dimensional characters. In the learning process, the student uses our training models, or creates his own, collects several videos for the portfolio, receives a set of universal tools to revitalize his ideas. Used programs on the course: 3ds Max, Adobe Photoshop, Adobe After Effects, Unity 3D.'),
('Основы 2D анимации', '2D animation basics', 7, 30, 1, true, 'Это курс для тех, кто хочет связать свою жизнь с анимацией. Студентам предстоит изучит базовые навыки и приемы 2D игровой анимации. В ходе обучения студент наработает собственное портфолио, получит все необходимые навыки и полное представление об особенностях аниматором и возможных дальнейших направлений развития. Используемые программы на курсе: Adobe Animate, Adobe Afer Effects, Esoteric Spine, Adobe Photoshop, Pureref.', 'This is a course for those who want to connect their lives with animation. Students will learn the basic skills and techniques of 2D game animation. During the training, the student will develop his own portfolio, get all the necessary skills and a complete idea of the features of the animator and possible further directions of development. Used programs on the course: Adobe Animate, Adobe Afer Effects, Esoteric Spine, Adobe Photoshop, Pureref.'),
('Разработка игр на Unity', 'Game development on Unity', 4, 60, 3, true, 'Разработка 2D игр на Unity позволит вам реализовать свои творческие способности в сфере создания игр. В процессе обучения студенты создадут несколько собственных проектов, которые смело можно добавить в свое портфолио и самостоятельно выпустить на разных платформах.', 'The development of 2D games on Unity will allow you to realize your creative abilities in the field of game creation. In the learning process, students will create several of their own projects, which you can safely add to your portfolio and independently release on different platforms.'),
('Основы гейм-дизайна', 'Game design basics', 4, 25, 3, true, 'Основы проектирования игр для тех, кто хочет начать работать в игровой индустрии, но пока не имеет для этого достаточно знаний. А также для тех кто желает сделать свою собственную успешную игру. Курс будет интересен как увлекающимся играми, желающим найти себя в профессии гейм-дизайнер, так и начинающим инди-разработчикам.', 'The basics of game design for those who want to start working in the gaming industry, but so far do not have enough knowledge for this. And also for those who want to make their own successful game. The course will be interesting both for those who are fond of games, who want to find themselves in the profession of a game designer, and for beginner indie developers.'),
('Дизайн игровых интерфейсов', 'Game interface design', 2, 35, 3, true, 'Курс создан для тех, кто хочет создавать красивые, а главное удобные в использовании игровые интерфейсы. На занятиях мы пройдем полный цикл разработки интерфейса, от скетчинга до тестирования основных экранов. Разработаем дизайн финальной графики интерфейсов и игровых иконок, используя навыки 2D художника.', 'The course was created for those who want to create beautiful, and most importantly, user-friendly game interfaces. In the classroom, we will go through the full cycle of interface development, from sketching to testing the main screens. We will design the final graphics for interfaces and game icons using the skills of a 2D artist.'),
('Обучение инструментов UI/UX дизайна', 'UI / UX design training tools', 5, 20, 4, true, 'Обучение созданию коммерческого дизайна сайтов и мобильных приложений. Курс готовит UX/UI-дизайнеров для веб-индустрии. После его окончания вы однозначно положите несколько качественных работ в свое портфолио. Используемые программы на курсе: Adobe Photoshop, Figma.', 'Training in creating commercial website design and mobile applications. The course prepares UX / UI designers for the web industry. After graduation, you will definitely put several quality work in your portfolio. Used programs on the course: Adobe Photoshop, Figma.'),
('Веб-дизайн и дизайн мобильных приложений', 'Web and mobile app design', 5, 20, 4, true, 'UI/UX и web-дизайн ориентирован на создание внешне привлекательных, удобных в использовании и функциональных пользовательских интерфейсов. Для того, чтобы достичь успеха в этой сфере, необходимо обладать художественным вкусом, быть внимательным к деталям, понимать принципы компьютерной графики и визуального дизайна. Используемые программы на курсе: Adobe Photoshop, Adobe Illustrator, Figma, Sketch.', 'UI / UX and web-design is focused on creating externally attractive, user-friendly and functional user interfaces. In order to achieve success in this area, you must have an artistic taste, be attentive to details, understand the principles of computer graphics and visual design. Used programs on the course: Adobe Photoshop, Adobe Illustrator, Figma, Sketch.'),
('Художник по персонажам базовый уровень', 'Character artist basic', 8, 35, 2, true, 'На этом курсе студентам предстоит освоить такое направление, как скульптинг (3D лепка), изучить Zbrush и дополнительные программы для текстуринга и рендера. Студенты детально изучат анатомию человека для улучшения качества своих работ. И конечно большое внимание уделяется дизайну персонажей, от пропорций до экипировки. Будет изучено 3D скульптуринг для игр и кино. Используемые программы на курсе: 3ds Max, Maya, Zbrush, Marmoset, Keyshot.', 'In this course, students will have to master such a direction as sculpting (3D modeling), study Zbrush and additional programs for texturing and rendering. Students will study human anatomy in detail to improve the quality of their work. And of course, much attention is paid to character design, from proportions to equipment. 3D sculpting for games and movies will be explored. Used programs on the course: 3ds Max, Maya, Zbrush, Marmoset, Keyshot.'),
('Художник по окружению базовый уровень', 'Basic environment designer', 6, 30, 2, true, 'Если вам нравиться 3D графика и хочется открыть для себя мир моделирования, то этот курс отлично подойдет для Вас! Будет изучен моделинг для видеоигр. В ходе курса будут рассмотрены основные возможности программ 3ds Max, Blender, 3D coat, Sketchfab.','If you like 3D graphics and want to discover the world of modeling, then this course is perfect for you! Modeling for video games will be studied. During the course, the main features of the 3ds Max, Blender, 3D coat, Sketchfab programs will be considered.');

insert into facultative.requirements(course_id, skill_id, level_id) values
(1, 1, 2), (1, 2, 1),
(2, 1, 2), (2, 2, 1),
(3, 1, 2), (3, 2, 1),
(4, 1, 1),
(5, 1, 3), (5, 2, 2),
(6, 1, 2), (6, 2, 1),
(7, 4, 1), (7, 6, 1),
(8, 1, 3), (8, 3, 1),
(9, 7, 1),
(11, 1, 2), (11, 2, 1), (11, 5, 1),
(13, 1, 2), (13, 5, 1),
(14, 1, 3), (14, 2, 2), (14, 4, 2), (14, 6, 2),
(15, 1, 3), (15, 2, 2), (15, 4, 2), (15, 6, 2);

insert into facultative.timetables(course_id, day_of_week, time) values
(1, 1, '16:30:00'), (1, 3, '16:30:00'),
(2, 2, '17:00:00'), (2, 4, '17:00:00'),
(3, 2, '10:00:00'), (3, 5, '10:00:00'),
(4, 2, '18:30:00'), (4, 5, '18:30:00'),
(5, 3, '17:30:00'),
(6, 6, '10:00:00'),
(7, 1, '16:00:00'), (7, 3, '16:00:00'), (7, 5, '16:00:00'),
(8, 2, '17:30:00'), (8, 4, '17:30:00'),
(9, 2, '17:00:00'), (9, 4, '17:00:00'), (9, 6, '11:00:00'),
(10, 4, '17:00:00'),
(11, 2, '10:00:00'), (11, 4, '10:00:00'),
(12, 1, '16:30:00'), (12, 5, '16:30:00'),
(13, 2, '18:00:00'), (13, 4, '18:00:00'),
(14, 1, '17:00:00'), (14, 4, '17:00:00'),
(15, 1, '11:30:00'), (15, 3, '11:30:00');

insert into facultative.archives(student_id, course_id, average_mark, start_date, end_date, review) values
(9, 2, 8, '2019-03-12', '2019-05-02', 'Студент усердно выполнял все задания на курсе.'),
(15, 2, 9, '2019-03-12', '2019-05-02', 'Студент прекрасно справился со всеми заданиями на курсе.'),
(14, 2, 6, '2019-03-12', '2019-05-02', 'Студент усердно выполнял все задания на курсе.'),
(18, 2, 7, '2019-03-12', '2019-05-02', 'Студент хорошо проявил себя.'),
(16, 2, 9, '2019-03-12', '2019-05-02', 'Студент прекрасно справился со всеми заданиями на курсе.'),
(10, 2, 8, '2019-03-12', '2019-05-02', 'Студент усердно выполнял все задания на курсе.'),

(9, 10, 6, '2019-01-20', '2019-04-02', 'Студент проявлял недостаточно желания к учебе.'),
(11, 10, 8, '2019-01-20', '2019-04-02', 'Студент усердно выполнял все задания на курсе.'),
(18, 10, 8, '2019-01-20', '2019-04-02', 'Студент усердно выполнял все задания на курсе.'),
(19, 10, 5, '2019-01-20', '2019-04-02', 'Студент проялвлял недостаточно желания к учебе.'),
(13, 10, 9, '2019-01-20', '2019-04-02', 'Студент прекрасно справился со всеми заданиями на курсе.'),
(12, 10, 8, '2019-01-20', '2019-04-02', 'Студент усердно выполнял все задания на курсе.'),

(10, 6, 6, '2019-05-10', '2019-06-25', 'Студент проявлял недостаточно желания к учебе.'),
(15, 6, 9, '2019-05-10', '2019-06-25', 'Студент прекрасно справился со всеми заданиями на курсе.'),
(14, 6, 7, '2019-05-10', '2019-06-25', 'Студент хорошо проявил себя.'),
(17, 6, 5, '2019-05-10', '2019-06-25', 'Студент проявлял недостаточно желания к учебе.'),
(11, 6, 9, '2019-05-10', '2019-06-25', 'Студент прекрасно справился со всеми заданиями на курсе.'),
(18, 6, 9, '2019-05-10', '2019-06-25', 'Студент прекрасно справился со всеми заданиями на курсе.');

insert into facultative.classes(course_id, date_time) values
(14, '2019-09-09 12:00'), (14, '2019-09-13 12:00'), (14, '2019-09-18 12:00'), (14, '2019-09-20 12:00'), (14, '2019-09-24 12:00'), (14, '2019-09-26 12:00'),
(12, '2019-08-07 16:30'), (12, '2019-08-10 16:30'), (12, '2019-08-15 16:30'), (12, '2019-08-17 16:30'), (12, '2019-08-21 16:30');

insert into facultative.applications(user_id, course_id, status_id) values
(9, 14, 3), (9, 12, 3),
(10, 14, 3),
(11, 14, 3), (11, 3, 1),
(12, 14, 3), (12, 12, 3),
(13, 1, 1), (13, 2, 3), (13, 3, 2),
(14, 14, 3), (14, 12, 3),
(15, 14, 3), (15, 12, 3),
(16, 14, 3), (16, 5, 1);

insert into facultative.works(course_id, title) values
(14, 'Входной тест'),
(14, 'Базовые элементы тест'),
(14, 'Базовые элементы практическое задание'),
(14, 'Отрисовка персонажа'),

(12, 'Входной тест'),
(12, 'Основные понятия UI/UX тест'),
(12, 'Правила изучения целевой аудитории'),
(12, 'Составление опросов');

insert into facultative.marks(student_id, work_id, mark) values
(9, 1, 8),
(9, 2, 7),
(9, 3, 8),
(9, 4, 6),
(10, 1, 4),
(10, 2, 8),
(10, 3, 3),
(10, 4, 5),
(11, 1, 6),
(11, 2, 7),
(11, 3, 8),
(11, 4, 5),
(12, 1, 9),
(12, 2, 9),
(12, 3, 8),
(12, 4, 6),
(14, 1, 7),
(14, 2, 8),
(14, 3, 6),
(14, 4, 5),
(15, 1, 7),
(15, 2, 8),
(15, 3, 7),
(15, 4, 7),
(16, 1, 8),
(16, 2, 9),
(16, 3, 9),
(16, 4, 7),

(9, 5, 5),
(9, 6, 5),
(9, 7, 6),
(9, 8, 7),
(12, 5, 8),
(12, 6, 7),
(12, 7, 6),
(12, 8, 7),
(14, 5, 8),
(14, 6, 9),
(14, 7, 9),
(14, 8, 8),
(15, 5, 9),
(15, 6, 7),
(15, 7, 6),
(15, 8, 7);*/