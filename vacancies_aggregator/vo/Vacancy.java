package com.javarush.task.task28.task2810.vo;


public class Vacancy {
    private String title, salary, city, companyName, siteName, url;
    
    
    public void setTitle(String title){
        this.title = title;
    }
    public void setSalary(String salary){
        this.salary = salary;
    }
    public void setCity(String city){
        this.city = city;
    }
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
    public void setSiteName(String siteName){
        this.siteName = siteName;
    }
    public void setUrl(String url){
        this.url = url;
    }
    
    public String getTitle(){
        return this.title;
    }
    public String getSalary(){
        return this.salary;
    }
    public String getCity(){
        return this.city;
    }
    public String getCompanyName(){
        return this.companyName;
    }
    public String getSiteName(){
        return this.siteName;
    }
    public String getUrl(){
        return this.url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vacancy)) return false;

        Vacancy vacancy = (Vacancy) o;

        if (title != null ? !title.equals(vacancy.title) : vacancy.title != null) return false;
        if (salary != null ? !salary.equals(vacancy.salary) : vacancy.salary != null) return false;
        if (city != null ? !city.equals(vacancy.city) : vacancy.city != null) return false;
        if (companyName != null ? !companyName.equals(vacancy.companyName) : vacancy.companyName != null) return false;
        if (siteName != null ? !siteName.equals(vacancy.siteName) : vacancy.siteName != null) return false;
        return url != null ? url.equals(vacancy.url) : vacancy.url == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (siteName != null ? siteName.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "title='" + title + '\'' +
                ", salary='" + salary + '\'' +
                ", city='" + city + '\'' +
                ", companyName='" + companyName + '\'' +
                ", siteName='" + siteName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}