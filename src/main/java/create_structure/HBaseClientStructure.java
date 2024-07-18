package create_structure;

import com.github.javafaker.Faker;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class HBaseClientStructure {

    public static void main(String[] args) throws IOException {

        Configuration conf = HBaseConfiguration.create();
        HBaseCustomClient client = new HBaseCustomClient(conf);

     // ==============================================================================================
     // CREATE COLLECTION
     // ==============================================================================================
        
        // Create Tabel Berita
        TableName tableBerita = TableName.valueOf("beritas");
        String[] beritas = {"main", "detail"};
        client.deleteTable(tableBerita);
        client.createTable(tableBerita, beritas);

        // Create Tabel Hewan
        TableName tableHewan = TableName.valueOf("hewans");
        String[] hewans = {"main", "petugas", "peternak", "kandang", "detail"};
        client.deleteTable(tableHewan);
        client.createTable(tableHewan, hewans);

        // Create Tabel Inseminasi
        TableName tableInseminasi = TableName.valueOf("inseminasis");
        String[] inseminasis = {"main", "peternak", "hewan", "petugas", "detail"};
        client.deleteTable(tableInseminasi);
        client.createTable(tableInseminasi, inseminasis);

        // Create Tabel Kandang
        TableName tableKandang = TableName.valueOf("kandangs");
        String[] kandangs = {"main", "peternak", "detail"};
        client.deleteTable(tableKandang);
        client.createTable(tableKandang, kandangs);

        // Create Tabel Kelahiran
        TableName tableKelahiran = TableName.valueOf("kelahirans");
        String[] kelahirans = {"main", "peternak", "hewan", "petugas", "detail"};
        client.deleteTable(tableKelahiran);
        client.createTable(tableKelahiran, kelahirans);

        // Create Tabel Pengobatan
        TableName tablePengobatan = TableName.valueOf("pengobatans");
        String[] pengobatans = {"main", "petugas", "detail"};
        client.deleteTable(tablePengobatan);
        client.createTable(tablePengobatan, pengobatans);

        // Create Tabel Peternak
        TableName tablePeternak = TableName.valueOf("peternaks");
        String[] peternaks = {"main", "user", "petugas", "detail"};
        client.deleteTable(tablePeternak);
        client.createTable(tablePeternak, peternaks);

        // Create Tabel Petugas
        TableName tablePetugas = TableName.valueOf("petugass");
        String[] petugass = {"main", "user", "detail"};
        client.deleteTable(tablePetugas);
        client.createTable(tablePetugas, petugass);

        // Create Tabel Pkb
        TableName tablePkb = TableName.valueOf("pkbs");
        String[] pkbs = {"main", "peternak", "hewan", "petugas", "detail"};
        client.deleteTable(tablePkb);
        client.createTable(tablePkb, pkbs);

        // Create Tabel Vaksin
        TableName tableVaksin = TableName.valueOf("vaksins");
        String[] vaksins = {"main", "peternak", "hewan", "petugas", "detail"};
        client.deleteTable(tableVaksin);
        client.createTable(tableVaksin, vaksins);

        // Create Table Users
        TableName tableUser = TableName.valueOf("users");
        String[] users = {"main", "detail"};
        client.deleteTable(tableUser);
        client.createTable(tableUser, users);

//        // Create Tabel 
//        TableName  = TableName.valueOf("s");
//        String[]  = { "main",  "detail" };
//        client.deleteTable();
//        client.createTable(, );
//        // Create Tabel Mata Kuliah
        TableName tableSubject = TableName.valueOf("subjects");
//        String[] subjects = { "main", "study_program", "subject_group", "detail" };
        client.deleteTable(tableSubject);
//        client.createTable(tableSubject, subjects);
//
//        // Create Tabel Bab
        TableName tableChapter = TableName.valueOf("chapters");
//        String[] chapters = { "main", "subject", "detail" };
        client.deleteTable(tableChapter);
//        client.createTable(tableChapter, chapters);
//
//        // Create Tabel Rumpun Mata Kuliah
        TableName tableSubjectGroup = TableName.valueOf("subject_groups");
//        String[] subjectGroups = { "main", "detail" };
        client.deleteTable(tableSubjectGroup);
//        client.createTable(tableSubjectGroup, subjectGroups);
//
//        // Create Tabel Dosen
        TableName tableLecture = TableName.valueOf("lectures");
//        String[] lectures = { "main", "study_program", "religion", "user", "detail" };
        client.deleteTable(tableLecture);
//        client.createTable(tableLecture, lectures);
//
//        // Create Tabel Mahasiswa
        TableName tableStudent = TableName.valueOf("students");
//        String[] students = { "main", "study_program", "religion", "user", "detail" };
        client.deleteTable(tableStudent);
//        client.createTable(tableStudent, students);
//
//        // Create Tabel RPS
        TableName tableRPS = TableName.valueOf("rps");
//        String[] RPS = { "main", "learning_media_softwares", "learning_media_hardwares", "requirement_subjects", "study_program", "subject", "dev_lecturers", "teaching_lecturers", "coordinator_lecturers", "ka_study_program", "detail" };
        client.deleteTable(tableRPS);
//        client.createTable(tableRPS, RPS);
//
//        // Create Tabel Detail RPS
        TableName tableRPSDetail = TableName.valueOf("rps_details");
//        String[] RPSDetails = { "main", "rps", "learning_materials", "form_learning", "learning_methods", "assignments", "estimated_times", "student_learning_experiences", "assessment_criterias", "appraisal_forms", "assessment_indicators", "detail" };
        client.deleteTable(tableRPSDetail);
//        client.createTable(tableRPSDetail, RPSDetails);
//
//        // Create Table Pustaka
        TableName tableReference = TableName.valueOf("references");
//        String[] references = { "main", "detail" };
        client.deleteTable(tableReference);
//        client.createTable(tableReference, references);
//
//        // Create Table Media Pembelajaran
        TableName tableLearningMedia = TableName.valueOf("learning_medias");
//        String[] learningMedias = { "main", "detail" };
        client.deleteTable(tableLearningMedia);
//        client.createTable(tableLearningMedia, learningMedias);
//
//        // Create Table Agama
        TableName tableReligion = TableName.valueOf("religions");
//        String[] religions = { "main", "detail" };
        client.deleteTable(tableReligion);
//        client.createTable(tableReligion, religions);
//
//        // Create Table Jurusan
        TableName tableDepartment = TableName.valueOf("departments");
//        String[] departments = { "main", "detail" };
        client.deleteTable(tableDepartment);
//        client.createTable(tableDepartment, departments);
//
//        // Create Table Prodi
        TableName tableStudyProgram = TableName.valueOf("study_programs");
//        String[] studyPrograms = { "main", "department", "detail" };
        client.deleteTable(tableStudyProgram);
//        client.createTable(tableStudyProgram, studyPrograms);
//
//        // Create Table Users
//        TableName tableUser = TableName.valueOf("users");
//        String[] users = { "main", "detail" };
//        client.deleteTable(tableUser);
//        client.createTable(tableUser, users);
//
//        // Create Table Bentuk Penilaian
        TableName tableAppraisalForm = TableName.valueOf("appraisal_forms");
//        String[] appraisalForms = { "main", "detail" };
        client.deleteTable(tableAppraisalForm);
//        client.createTable(tableAppraisalForm, appraisalForms);
//
//        // Create Tabel Kriteria Penilaian
        TableName tableAssessmentCriteria = TableName.valueOf("assessment_criterias");
//        String[] assessmentCriterias = { "main", "detail" };
        client.deleteTable(tableAssessmentCriteria);
//        client.createTable(tableAssessmentCriteria, assessmentCriterias);
//
//        // Create Tabel Bentuk Pembelajaran
        TableName tableFormLearning = TableName.valueOf("form_learnings");
//        String[] formLearnings = { "main", "detail" };
        client.deleteTable(tableFormLearning);
//        client.createTable(tableFormLearning, formLearnings);
//
//        // Create Tabel Metode Pembelajaran
        TableName tableLearningMethod = TableName.valueOf("learning_methods");
//        String[] learningMethods = { "main", "detail" };
        client.deleteTable(tableLearningMethod);
//        client.createTable(tableLearningMethod, learningMethods);
//
//        // Create Tabel Pertanyaan
        TableName tableQuestion = TableName.valueOf("questions");
//        String[] questions = { "main", "rps_detail", "detail" };
        client.deleteTable(tableQuestion);
//        client.createTable(tableQuestion, questions);
//
//        // Create Tabel Jawaban
        TableName tableAnswer = TableName.valueOf("answers");
//        String[] answers = { "main", "question", "detail" };
        client.deleteTable(tableAnswer);
//        client.createTable(tableAnswer, answers);
//
//        // Create Tabel Ujian
        TableName tableExam = TableName.valueOf("exams");
//        String[] exams = { "main", "rps", "questions", "detail" };
        client.deleteTable(tableExam);
//        client.createTable(tableExam, exams);
//
//        // Create Tabel Kuis
        TableName tableQuizzes = TableName.valueOf("quizzes");
//        String[] quizzes = { "main", "rps", "questions", "detail" };
        client.deleteTable(tableQuizzes);
//        client.createTable(tableQuizzes, quizzes);
//
//        // Create Tabel Latihan
        TableName tableExcercise = TableName.valueOf("exercises");
//        String[] exercises = { "main", "rps", "questions", "detail" };
        client.deleteTable(tableExcercise);
//        client.createTable(tableExcercise, exercises);
//
//        // Create Tabel Percobaan pengumpulan Ujian
        TableName tableExamAttempts = TableName.valueOf("exam_attempts");
//        String[] examAttempts = { "main", "exam", "user", "student", "student_answers", "detail" };
        client.deleteTable(tableExamAttempts);
//        client.createTable(tableExamAttempts, examAttempts);
//
//        // Create Tabel Percobaan pengumpulan Kuis
        TableName tableQuizAttempts = TableName.valueOf("quiz_attempts");
//        String[] quizAttempts = { "main", "quiz", "user", "student", "student_answers", "detail" };
        client.deleteTable(tableQuizAttempts);
//        client.createTable(tableQuizAttempts, quizAttempts);
//
//        // Create Tabel Percobaan pengumpulan Latihan
        TableName tableExerciseAttempts = TableName.valueOf("exercise_attempts");
//        String[] exerciseAttempts = { "main", "exercise", "user", "student", "student_answers", "detail" };
        client.deleteTable(tableExerciseAttempts);
//        client.createTable(tableExerciseAttempts, exerciseAttempts);
        TableName tablecpl_study_programs = TableName.valueOf("cpl_study_programs");
        client.deleteTable(tablecpl_study_programs);
        TableName tablecpl_subjects = TableName.valueOf("cpl_subjects");
        client.deleteTable(tablecpl_subjects);

        // seeder
        // time now
        ZoneId zoneId = ZoneId.of("Asia/Jakarta");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
        Instant instant = zonedDateTime.toInstant();

        // Insert Users
        client.insertRecord(tableUser, "USR001", "main", "id", "USR001");
        client.insertRecord(tableUser, "USR001", "main", "name", "Senja Abdi");
        client.insertRecord(tableUser, "USR001", "main", "username", "admin");
        client.insertRecord(tableUser, "USR001", "main", "email", "admin@gmail.com");
        client.insertRecord(tableUser, "USR001", "main", "password", "$2a$10$SDRWMUk.2fnli0GTmqodJexjRksTw0En98dU8fdKsw7nTbZzMrj.2"); // password
        client.insertRecord(tableUser, "USR001", "main", "role", "1");
        client.insertRecord(tableUser, "USR001", "detail", "createdAt", "Senja007");

        client.insertRecord(tableUser, "USR002", "main", "id", "USR002");
        client.insertRecord(tableUser, "USR002", "main", "name", "petugas");
        client.insertRecord(tableUser, "USR002", "main", "username", "petugas");
        client.insertRecord(tableUser, "USR002", "main", "email", "petugas@gmail.com");
        client.insertRecord(tableUser, "USR002", "main", "password", "$2a$10$SDRWMUk.2fnli0GTmqodJexjRksTw0En98dU8fdKsw7nTbZzMrj.2"); // password
        client.insertRecord(tableUser, "USR002", "main", "role", "2");
        client.insertRecord(tableUser, "USR002", "detail", "createdAt", "Senja007");

        client.insertRecord(tableUser, "USR003", "main", "id", "USR003");
        client.insertRecord(tableUser, "USR003", "main", "name", "peternak");
        client.insertRecord(tableUser, "USR003", "main", "username", "peternak");
        client.insertRecord(tableUser, "USR003", "main", "email", "peternak@gmail.com");
        client.insertRecord(tableUser, "USR003", "main", "password", "$2a$10$SDRWMUk.2fnli0GTmqodJexjRksTw0En98dU8fdKsw7nTbZzMrj.2"); // password
        client.insertRecord(tableUser, "USR003", "main", "role", "3");
        client.insertRecord(tableUser, "USR003", "detail", "createdAt", "Senja007");

//        
//        // Insert Petugas 
//client.insertRecord(tablePetugas, "PTG001", "main", "nikPetugas", "PTG001");
//client.insertRecord(tablePetugas, "PTG001", "main", "namaPetugas", "sunaryam");
//client.insertRecord(tablePetugas, "PTG001", "main", "noTelp", "123456789");
//client.insertRecord(tablePetugas, "PTG001", "main", "email", "petugas1@gmail.com");
////client.insertRecord(tablePetugas, "PTG001", "user", "id", "USR002"); // Assign user with ID USR002 as the admin who created this petugas
//client.insertRecord(tablePetugas, "PTG001", "detail", "created_by", "admin");
//
//client.insertRecord(tablePetugas, "PTG002", "main", "nikPetugas", "PTG002");
//client.insertRecord(tablePetugas, "PTG002", "main", "namaPetugas", "sunardi");
//client.insertRecord(tablePetugas, "PTG002", "main", "noTelp", "987654321");
//client.insertRecord(tablePetugas, "PTG002", "main", "email", "petugas2@gmail.com");
////client.insertRecord(tablePetugas, "PTG002", "user", "id", "USR003"); // Assign user with ID USR003 as the admin who created this petugas
//client.insertRecord(tablePetugas, "PTG002", "detail", "created_by", "admin");
//
//client.insertRecord(tablePetugas, "PTG003", "main", "nikPetugas", "PTG003");
//client.insertRecord(tablePetugas, "PTG003", "main", "namaPetugas", "kimin");
//client.insertRecord(tablePetugas, "PTG003", "main", "noTelp", "555555555");
//client.insertRecord(tablePetugas, "PTG003", "main", "email", "petugas3@gmail.com");
////client.insertRecord(tablePetugas, "PTG003", "user", "id", "USR001"); // Assign user with ID USR001 as the admin who created this petugas
//client.insertRecord(tablePetugas, "PTG003", "detail", "created_by", "admin");
    }
}
