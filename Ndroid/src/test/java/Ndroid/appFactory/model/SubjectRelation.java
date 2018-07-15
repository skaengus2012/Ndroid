package Ndroid.appFactory.model;

/**
 * Created by Doohyun on 2017. 3. 5..
 */

public class SubjectRelation {
    private Integer memberSubjectSn;
    private Integer organizationSubjectSn;
    private Integer companySubjectSn;
    private String memberName;

    public SubjectRelation(
            Integer companySubjectSn
            , Integer memberSubjectSn
            , String memberName
            , Integer organizationSubjectSn) {
        this.companySubjectSn = companySubjectSn;
        this.memberName = memberName;
        this.organizationSubjectSn = organizationSubjectSn;
        this.memberSubjectSn = memberSubjectSn;
    }

    public Integer getMemberSubjectSn() {
        return memberSubjectSn;
    }

    public Integer getOrganizationSubjectSn() {
        return organizationSubjectSn;
    }

    public Integer getCompanySubjectSn() {
        return companySubjectSn;
    }

    public String getMemberName() {
        return memberName;
    }
}
