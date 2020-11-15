package com.javawebinar.webapp;

import com.javawebinar.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {
    public static Resume createFilledResume (String uuid, String fullName) {
        Resume r1 = new Resume(uuid, fullName);
        AbstractSection objective1 = new SimpleTextSection("Ведущий стажировок и корпоративного обучения " +
                "по Java Web и Enterprise технологиям");
        AbstractSection personal1 = new SimpleTextSection("Аналитический склад ума, сильная логика, креативность, " +
                "инициативность. Пурист кода и архитектуры.");

        List<String> listAchievements = new ArrayList<>();
        listAchievements.add("С 2013 года: разработка проектов \"Разработка Web приложения\"," +
                "\"Java Enterprise\"," +
                " \"Многомодульный maven. Многопоточность. XML (JAXB/StAX)." +
                " Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". " +
                "Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        listAchievements.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. " +
                "Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        listAchievements.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. " +
                "Интеграция с 1С, Bonita BPM, CMIS, LDAP. " +
                "Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery." +
                " Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        listAchievements.add("Реализация c нуля Rich Internet Application приложения на стеке технологий" +
                " JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        listAchievements.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных " +
                "сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). " +
                "Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. " +
                "Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        listAchievements.add("Реализация протоколов по приему платежей всех основных платежных системы России" +
                " (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        AbstractSection achievements1 = new BulletedListSection(listAchievements);

        List<String> listQualifications = new ArrayList<>();
        listQualifications.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        listQualifications.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        listQualifications.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        listQualifications.add("MySQL, SQLite, MS SQL, HSQLDB");
        listQualifications.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,");
        listQualifications.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,");
        listQualifications.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, " +
                "Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), " +
                "Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, " +
                "Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        listQualifications.add("Python: Django.");
        listQualifications.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        listQualifications.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        listQualifications.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX," +
                " DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, " +
                "CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        listQualifications.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix,");
        listQualifications.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, " +
                "Nagios, iReport, OpenCmis, Bonita, pgBouncer.");
        listQualifications.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, " +
                "архитектурных шаблонов, UML, функционального программирования");
        listQualifications.add("Родной русский, английский \"upper intermediate\"");
        AbstractSection qualifications1 = new BulletedListSection(listQualifications);


        List<Organization> organizations = new ArrayList<>();
        List<Position> listPos1 = new ArrayList<>();
        Position pos1_1 = new Position("Java Online Projects", LocalDate.of(2013, 10, 1),
                LocalDate.now(), "Автор проекта.\n Создание, организация и проведение Java онлайн проектов и стажировок.");
        listPos1.add(pos1_1);
        Organization org1 = new Organization(listPos1, "Java Online Projects" ,"https://javaops.ru/");
        List<Position> listPos2 = new ArrayList<>();
        Position pos2_1 = new Position("Wrike", LocalDate.of(2014, 4, 1), LocalDate.of(2016, 1, 1),
                "Проектирование и разработка онлайн платформы управления проектами" +
                        " Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis)." +
                        " Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        listPos2.add(pos2_1);
        Organization org2 = new Organization(listPos2, "Wrike","www.wrike.com//");
        List<Position> listPos3 = new ArrayList<>();
        Position pos3_1 = new Position("RIT Center", LocalDate.of(2012, 4, 1),
                LocalDate.of(2014, 10, 1), "Организация процесса разработки системы ERP" +
                " для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), " +
                "миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. " +
                "Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, " +
                "BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). " +
                "Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin " +
                "development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, " +
                "Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");
        listPos3.add(pos3_1);
        Organization org3 = new Organization(listPos3, "RIT Center", "link");
        List<Position> listPos4 = new ArrayList<>();
        Position pos4_1 = new Position("Luxoft (Deutsche Bank)", LocalDate.of(2010, 12, 1),
                LocalDate.of(2012, 4, 1), "Ведущий программист\n" +
                "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, " +
                "Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для " +
                "администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, " +
                "Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.");
        listPos4.add(pos4_1);
        Organization org4 = new Organization(listPos4, "Luxoft" , "https://luxoft.ru");
        List<Position> listPos5 = new ArrayList<>();
        Position pos5_1 = new Position("Yota", LocalDate.of(2008, 6, 1),
                LocalDate.of(2010, 12, 1), "Ведущий специалист\n" +
                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" " +
                "(GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). " +
                "Реализация администрирования, статистики и мониторинга фреймворка. " +
                "Разработка online JMX клиента (Python/ Jython, Django, ExtJS)");
        listPos5.add(pos5_1);
        Organization org5 = new Organization(listPos5, "Yota" ,"https://www.yota.ru/");
        List<Position> listPos6 = new ArrayList<>();
        Position pos6_1 = new Position("Enkata", LocalDate.of(2007, 3, 1),
                LocalDate.of(2008, 6, 1), "\tРазработчик ПО\n" +
                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS)" +
                " частей кластерного J2EE приложения (OLAP, Data mining).");
        listPos6.add(pos6_1);
        Organization org6 = new Organization(listPos6, "Enkata","http://enkata.com/");
        List<Position> listPos7 = new ArrayList<>();
        Position pos7_1 = new Position("Siemens AG", LocalDate.of(2005, 1, 1),
                LocalDate.of(2007, 2, 1), "Разработчик ПО\n" +
                "Разработка информационной модели, проектирование интерфейсов, " +
                "реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).");
        listPos7.add(pos7_1);
        Organization org7 = new Organization(listPos7, "Siemens AG" ,"https://new.siemens.com/ru/ru.html");
        List<Position> listPos8 = new ArrayList<>();
        Position pos8_1 = new Position("Alcatel", LocalDate.of(1997, 9, 1),
                LocalDate.of(2005, 1, 1), "Инженер по аппаратному и программному тестированию\n" +
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).");
        listPos8.add(pos8_1);
        Organization org8 = new Organization(listPos8, "Alcatel", "http://www.alcatel.ru/");
        List<Position> listPos9 = new ArrayList<>();
        Position pos9_1 = new Position("Coursera", LocalDate.of(2013, 3, 1),
                LocalDate.of(2013, 5, 1), "\"Functional Programming Principles" +
                " in Scala\" by Martin Odersky");
        listPos9.add(pos9_1);
        Organization org9 = new Organization(listPos9, "Coursera","https://www.coursera.org/");
        List<Position> listPos10 = new ArrayList<>();
        Position pos10_1 = new Position("Luxoft", LocalDate.of(2011, 3, 1),
                LocalDate.of(2011, 4, 1), "Курс \"Объектно-ориентированный анализ ИС. " +
                "Концептуальное моделирование на UML.\"");
        listPos10.add(pos10_1);
        Organization org10 = new Organization(listPos10, "Luxoft", "https://luxoft.ru");
        List<Position> listPos11 = new ArrayList<>();
        Position pos11_1 = new Position("Siemens AG", LocalDate.of(2005, 1, 1),
                LocalDate.of(2015, 4, 1), "3 месяца обучения мобильным IN сетям (Берлин)");
        listPos11.add(pos11_1);
        Organization org11 = new Organization(listPos11, "Siemens AG", "https://new.siemens.com/ru/ru.html");
        List<Position> listPos12 = new ArrayList<>();
        Position pos12_1 = new Position("Alcatel", LocalDate.of(1997, 9, 1),
                LocalDate.of(1998, 3, 1), "6 месяцев обучения цифровым телефонным сетям (Москва)");
        listPos12.add(pos12_1);
        Organization org12 = new Organization(listPos12, "Alcatel","http://www.alcatel.ru/");
        List<Position> listPos13 = new ArrayList<>();
        Position pos13_1 = new Position("Санкт-Петербургский национальный исследовательский университет " +
                "информационных технологий, механики и оптики", LocalDate.of(1993, 9, 1),
                LocalDate.of(1996, 7, 1), "Аспирантура (программист С, С++)");
        Position pos13_2 = new Position("Санкт-Петербургский национальный исследовательский университет " +
                "информационных технологий, механики и оптики", LocalDate.of(1987, 9, 1),
                LocalDate.of(1993, 7, 1), "Инженер (программист Fortran, C)");
        listPos13.add(pos13_1);
        listPos13.add(pos13_2);
        Organization org13 = new Organization(listPos13, "Санкт-Петербургский национальный исследовательский университет ","https://itmo.ru/ru/");
        List<Position> listPos14 = new ArrayList<>();
        Position pos14_1 = new Position("Заочная физико-техническая школа при МФТИ",
                LocalDate.of(1984, 9, 1), LocalDate.of(1987, 6, 1),
                "Закончил с отличием");
        listPos14.add(pos14_1);
        Organization org14 = new Organization(listPos14, "Заочная физико-техническая школа при МФТИ","http://www.school.mipt.ru/");

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
