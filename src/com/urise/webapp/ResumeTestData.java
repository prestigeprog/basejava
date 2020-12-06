package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {
    public static Resume createFilledResume(String uuid, String fullName) {
        Resume r1 = new Resume(uuid, fullName);
        AbstractSection objective1 = new SimpleTextSection("Ведущий стажировок и корпоративного обучения " +
                "по Java Web и Enterprise технологиям");
        AbstractSection personal1 = new SimpleTextSection("Аналитический склад ума, сильная логика, креативность, " +
                "инициативность. Пурист кода и архитектуры.");

        AbstractSection achievements1 = new BulletedListSection("С 2013 года: разработка проектов \"Разработка Web приложения\"," +
                "\"Java Enterprise\"," +
                " \"Многомодульный maven. Многопоточность. XML (JAXB/StAX)." +
                " Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". " +
                "Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.", "Реализация двухфакторной " +
                "аутентификации для онлайн платформы управления проектами Wrike. " +
                "Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.", "Налаживание процесса " +
                "разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. " +
                "Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery." +
                " Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.",
                "Реализация c нуля Rich Internet Application приложения на стеке технологий" +
                        " JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.",
                "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных " +
                        "сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). " +
                        "Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. " +
                        "Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Python/ Django).",
                "Реализация протоколов по приему платежей всех основных платежных системы России" +
                        " (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");

        AbstractSection qualifications1 = new BulletedListSection("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                "Version control: Subversion, Git, Mercury, ClearCase, Perforce",
                "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,", "MySQL, SQLite, MS SQL, HSQLDB",
                "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,", "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,",
                "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, " +
                        "Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), " +
                        "Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, " +
                        "Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).", "Python: Django.",
                "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js", "Scala: SBT, Play2, Specs2, Anorm, Spray, Akka",
                "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX," +
                        " DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, " +
                        "CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.", "Инструменты: Maven + plugin development, Gradle, настройка Ngnix,",
                "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, " +
                        "Nagios, iReport, OpenCmis, Bonita, pgBouncer.", "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, " +
                "архитектурных шаблонов, UML, функционального программирования", "Родной русский, английский \"upper intermediate\"");


        List<Organization> organizations = new ArrayList<>();
        // , ,"https://javaops.ru/"
        Organization org1 = new Organization("Java Online Projects", null,
                new Organization.Position(2013, Month.OCTOBER, "Java Online Projects",
                        "Автор проекта.\n Создание, организация и проведение Java онлайн проектов и стажировок."));
        Organization org2 = new Organization("Wrike", "www.wrike.com//",
                new Organization.Position(2014, Month.APRIL, 2016, Month.JANUARY, "Wrike",
                        "Проектирование и разработка онлайн платформы управления проектами" +
                                " Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis)." +
                                " Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."));
        Organization org3 = new Organization("RIT Center", "link", new Organization.Position(2012, Month.APRIL,
                2014, Month.OCTOBER, "RIT Center", "Организация процесса разработки системы ERP" +
                " для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), " +
                "миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. " +
                "Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, " +
                "BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). " +
                "Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin " +
                "development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, " +
                "Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python"));
        Organization org4 = new Organization("Luxoft", "https://luxoft.ru", new Organization.Position(2010, Month.DECEMBER,
                2012, Month.APRIL, "Luxoft (Deutsche Bank)", "Ведущий программист\n" +
                "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, " +
                "Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для " +
                "администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, " +
                "Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5."));
        Organization org5 = new Organization("Yota", "https://www.yota.ru/", new Organization.Position(2008, Month.JUNE,
                2010, Month.DECEMBER, "Yota", "Ведущий специалист\n" +
                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" " +
                "(GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). " +
                "Реализация администрирования, статистики и мониторинга фреймворка. " +
                "Разработка online JMX клиента (Python/ Jython, Django, ExtJS)"));
        Organization org6 = new Organization("Enkata", "http://enkata.com/", new Organization.Position(2007, Month.MARCH,
                2008, Month.JUNE, "Enkata", "\tРазработчик ПО\n" +
                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS)" +
                " частей кластерного J2EE приложения (OLAP, Data mining)."));
        Organization org7 = new Organization("Siemens AG", "https://new.siemens.com/ru/ru.html", new Organization.Position(
                2005, Month.JANUARY, 2007, Month.FEBRUARY, "Siemens AG", "Разработчик ПО\n" +
                "Разработка информационной модели, проектирование интерфейсов, " +
                "реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix)."));
        Organization org8 = new Organization("Alcatel", "http://www.alcatel.ru/", new Organization.Position(1997, Month.SEPTEMBER,
                2005, Month.JANUARY, "Alcatel", "Инженер по аппаратному и программному тестированию\n" +
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)."));
        Organization org9 = new Organization("Coursera", "https://www.coursera.org/",
                new Organization.Position(2013, Month.MARCH, 2013, Month.MAY, "Coursera",
                        "\"Functional Programming Principles in Scala\" by Martin Odersky"));
        Organization org10 = new Organization("Luxoft", "https://luxoft.ru", new Organization.Position(2011, Month.MARCH,
                2011, Month.APRIL, "Luxoft", "Курс \"Объектно-ориентированный анализ ИС. " +
                "Концептуальное моделирование на UML.\""));
        Organization org11 = new Organization("Siemens AG", "https://new.siemens.com/ru/ru.html", new Organization.Position(2005, Month.JANUARY,
                2015, Month.APRIL, "Siemens AG", "3 месяца обучения мобильным IN сетям (Берлин)"));
        Organization org12 = new Organization("Alcatel", "http://www.alcatel.ru/", new Organization.Position(1997, Month.SEPTEMBER,
                1998, Month.MARCH, "Alcatel", "6 месяцев обучения цифровым телефонным сетям (Москва)"));
        Organization org13 = new Organization("Санкт-Петербургский национальный исследовательский университет ", "https://itmo.ru/ru/", new Organization.Position(1993, Month.SEPTEMBER,
                1996, Month.JULY, "Санкт-Петербургский национальный исследовательский университет " +
                "информационных технологий, механики и оптики", "Аспирантура (программист С, С++)"),
                new Organization.Position(1987, Month.SEPTEMBER, 1993, Month.JULY,
                        "Санкт-Петербургский национальный исследовательский университет информационных технологий, " +
                                "механики и оптики", "Инженер (программист Fortran, C)"));
        Organization org14 = new Organization("Заочная физико-техническая школа при МФТИ",
                "http://www.school.mipt.ru/", new Organization.Position(1984, Month.SEPTEMBER, 1987, Month.JUNE,
                "Заочная физико-техническая школа при МФТИ", "Закончил с отличием"));

        organizations.add(org1);
        organizations.add(org2);
        organizations.add(org3);
        organizations.add(org4);
        organizations.add(org5);
        organizations.add(org6);
        organizations.add(org7);
        organizations.add(org8);
        organizations.add(org9);

        List<Organization> eduList = new ArrayList<>();

        eduList.add(org10);
        eduList.add(org11);
        eduList.add(org12);
        eduList.add(org13);
        eduList.add(org14);

        AbstractSection work1 = new OrganizationSection(organizations);
        AbstractSection edu1 = new OrganizationSection(eduList);

        String contact1 = "+7(921) 855-0482"; //phone
        String contact2 = "grigory.kislin"; //skype
        String contact3 = "gkislin@yandex.ru"; //email
        String contact4 = "linkedin: https://www.linkedin.com/in/gkislin"; //linkedin
        String contact5 = "github: https://github.com/gkislin"; //github
        String contact6 = "stackoverflow: https://stackoverflow.com/users/548473/grigory-kislin"; //stackoverflow
        String contact7 = "http://gkislin.ru/";//homepage

        r1.setContact(ContactType.PHONE, contact1);
        r1.setContact(ContactType.SKYPE, contact2);
        r1.setContact(ContactType.EMAIL, contact3);
        r1.setContact(ContactType.LINKEDIN, contact4);
        r1.setContact(ContactType.GITHUB, contact5);
        r1.setContact(ContactType.STACKOVERFLOW, contact6);
        r1.setContact(ContactType.HOMEPAGE, contact7);

        r1.setSection(SectionType.PERSONAL, personal1);
        r1.setSection(SectionType.OBJECTIVE, objective1);
        r1.setSection(SectionType.ACHIEVEMENT, achievements1);
        r1.setSection(SectionType.QUALIFICATIONS, qualifications1);
        r1.setSection(SectionType.EXPERIENCE, work1);
        r1.setSection(SectionType.EDUCATION, edu1);
        return r1;
    }

    public static void main(String[] args) {
        Resume r1 = createFilledResume("uuid1", "Григорий Кислин");

        System.out.println(r1.getContact(ContactType.PHONE));
        System.out.println(r1.getContact(ContactType.SKYPE));
        System.out.println(r1.getContact(ContactType.EMAIL));
        System.out.println(r1.getContact(ContactType.LINKEDIN));
        System.out.println(r1.getContact(ContactType.GITHUB));
        System.out.println(r1.getContact(ContactType.STACKOVERFLOW));
        System.out.println(r1.getContact(ContactType.HOMEPAGE));

        System.out.println(r1.getSection(SectionType.PERSONAL));
        System.out.println(r1.getSection(SectionType.OBJECTIVE));
        System.out.println(r1.getSection(SectionType.ACHIEVEMENT));
        System.out.println(r1.getSection(SectionType.QUALIFICATIONS));
        System.out.println(r1.getSection(SectionType.EXPERIENCE));
        System.out.println(r1.getSection(SectionType.EDUCATION));


    }
}
