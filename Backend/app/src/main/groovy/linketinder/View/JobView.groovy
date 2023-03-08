package linketinder.View

import linketinder.Controller.CandidateQualificationController
import linketinder.Controller.EnterpriseController
import linketinder.Controller.JobController
import linketinder.Controller.JobQualificationController
import linketinder.Model.DAO.CandidateQualificationDAO
import linketinder.Model.DAO.EnterpriseDAO
import linketinder.Model.DAO.JobDAO
import linketinder.Model.DAO.JobQualificationDAO
import linketinder.Model.DAO.UserDAO
import linketinder.Model.Entity.CandidateQualification
import linketinder.Model.Entity.Job
import linketinder.Model.Entity.JobQualification
import linketinder.Model.Entity.User
import linketinder.Utils.Regex

class JobView {

    static menu(User user) {
        EnterpriseController enterpriseController = new EnterpriseController(new EnterpriseDAO(), new UserDAO())
        JobController jobController = new JobController(new JobDAO())
        JobQualificationController jobQualificationController = new JobQualificationController(new JobQualificationDAO())
        int enterprise_id = enterpriseController.getId(user.id)
        Scanner input = new Scanner(System.in)

        println ""
        println "1 - Cadastrar job"
        println "2 - Ver jobs"
        println "3 - Editar job"
        println "4 - Deletar job"
        println "0 - Sair"
        println ""
        println "Digite o codigo do comando"
        String command = input.nextLine()

        switch (command) {
            case "0":
                Menu.start()
                break
            case "1":
                println("Digite a descricao para o job")
                String jobDescription = input.nextLine()
                Job newJob = new Job(description: jobDescription, enterprise_id: enterprise_id)
                List<List<Job>> job =jobController.createJob(newJob)
                addJobQualification(jobQualificationController, job[0][0] as int)
                println(job)
                menu(user)
                break
            case "2":
                List<Job> jobs = jobController.listEnterpriseJobs(enterprise_id)
                jobs.forEach {
                    jobQualificationController.getJobDescription(it as Job)
                }
                goBack(user)
                break
            default:
                break
        }
    }

    static goBack(User user) {
        System.out.println("")
        System.out.println("________________________________")
        System.out.println("Digite 1 para voltar ao menu")
        Scanner input = new Scanner(System.in)
        String data = input.nextLine()

        switch (data) {
            case "1":
                menu(user)
                break
            default:
                input.close()
                break
        }
    }

    static String needJava(Scanner input){
        println("Vaga precisa de conhecimento em JAVA? (0-NAO/1-SIM)")
        String java = input.nextLine()
        while(!(java ==~ Regex.options)){
            println("Vaga precisa de conhecimento em JAVA? (0-NAO/1-SIM)")
            java = input.nextLine()
        }
        return java
    }

    static String needGroovy(Scanner input) {
        println("Vaga precisa de conhecimento em Groovy? (0-NAO/1-SIM)")
        String groovy = input.nextLine()
        while(!(groovy ==~ Regex.options)){
            println("Vaga precisa de conhecimento em Groovy? (0-NAO/1-SIM)")
            groovy = input.nextLine()
        }
        return groovy
    }

    static String needJavascript(Scanner input) {
        println("Vaga precisa de conhecimento em Javascript? (0-NAO/1-SIM)")
        String javascript = input.nextLine()
        while(!(javascript ==~ Regex.options)){
            println("Vaga precisa de conhecimento em Javascript? (0-NAO/1-SIM)")
            javascript = input.nextLine()
        }
        return javascript
    }

    static String needPhyton(Scanner input) {
        println("Vaga precisa de conhecimento em Phyton? (0-NAO/1-SIM)")
        String phyton = input.nextLine()
        while(!(phyton ==~ Regex.options)){
            println("Vaga precisa de conhecimento em Phyton? (0-NAO/1-SIM)")
            phyton = input.nextLine()
        }
        return phyton
    }

    static void addJobQualification(JobQualificationController jobQualificationController, int job_id){
        int JAVA_ID = 1
        int GROOVY_ID = 2
        int JAVASCRIPT_ID = 3
        int PHYTON_ID = 4
        Scanner input = new Scanner(System.in)
        String java = needJava(input)
        if(java ==~ Regex.optionYes){
            JobQualification jobQualification = new JobQualification(job_id: job_id, qualification_id: JAVA_ID)
            jobQualificationController.addJobQualification(jobQualification)
        }

        String groovy = needGroovy(input)
        if(groovy ==~ Regex.optionYes){
            JobQualification jobQualification = new JobQualification(job_id: job_id, qualification_id: GROOVY_ID)
            jobQualificationController.addJobQualification(jobQualification)
        }

        String javascript = needJavascript(input)
        if(javascript ==~ Regex.optionYes){
            JobQualification jobQualification = new JobQualification(job_id: job_id, qualification_id: JAVASCRIPT_ID)
            jobQualificationController.addJobQualification(jobQualification)
        }

        String phyton = needPhyton(input)
        if(phyton ==~ Regex.optionYes){
            JobQualification jobQualification = new JobQualification(job_id: job_id, qualification_id: PHYTON_ID)
            jobQualificationController.addJobQualification(jobQualification)
        }
    }

}
