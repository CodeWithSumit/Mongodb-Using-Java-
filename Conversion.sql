Select Lab_Name,Lab_Accession_NR,LabSpecimenNumber,FieldSpecimenNumber,SpecTypeCode,TestDate,TestInterp
(Select 
      CRMAF_lms_lms_result.lms_spm_spm_18 DATE_SPEC_RECVD
	 ,CRMAF_lms_lms_result._____________  MSG_DATE_RECVD ----Don't find this field in the mapping query given
	 ,lab.lms_symbol Lab_Name
	 ,lab.lms_pv1_pv1_19_cx_1 Lab_Accession_NR        --- Just an assumption that data coming from same table as Lab Name
	 ,CRMAF_lms_lms_result.lms_spm_spm_2_eip_1_ei_1 FieldSpecimenNumber
	 ,pid3.lms_pid_pid3_cx1 AnimalID
	 ,CRMAF_lms_lms_result.lms_spm_spm_2_eip_2_ei_1 LabSpecimenNumber
	 ,CRMAF_lms_lms_result.lms_spm_spm_4_cwe_1 SpecTypeCode
	 ,CRMAF_lms_lms_result.lms_obx_obx_19 TestDate
	 ,CRMAF_lms_lms_result.lms_obx_obx_8 TestInterp
	 ,ROW_NUMBER() OVER (PARTITION BY lab.lms_symbol, lab.lms_pv1_pv1_19_cx_1, CRMAF_lms_lms_result.lms_spm_spm_2_eip_2_ei_1, CRMAF_lms_lms_result.lms_spm_spm_2_eip_1_ei_1, CRMAF_lms_lms_result.lms_spm_spm_4_cwe_1
	  ORDER BY CRMAF_lms_lms_result.lms_obx_obx_19 DESC,
	  CRMAF_lms_lms_result._____________ DESC,
	  (CASE
	  WHEN CRMAF_lms_lms_result.lms_obx_obx_8 LIKE '%POS%' THEN 1
	  ELSE 2
	  END) ASC
	  ) AS TEST_DATE_ORDER
     From Filteredlms_lms_result AS CRMAF_lms_lms_result
	 Left Join Filteredlms_lab lab on CRMAF_lms_lms_result.lms_msh_msh_4_hd_1 = lab.lms_nais
	 left join Filteredlms_pid_pid3 pid3 on CRMAF_lms_lms_result.lms_patientid = pid3.lms_pid_pid3id
	 WHERE TEST_GROUP = 'VSAI'
	--AND PROGRAM_OID = '2.16.840.1.113883.3.5.8.4.2' --AI LMB SURVEILLANCE
	AND CRMAF_lms_lms_result.lms_obx_obx_3_cwe_1 = '44263-2'
	AND _______________ DELETED IS NULL   -- Don't know where this deleted column is coming from
	AND CRMAF_lms_lms_result.lms_obx_obx_8 <> 'X'
	AND CRMAF_lms_lms_result._____________ BETWEEN :START_DATE AND :END_DATE
	ORDER BY lab.lms_symbol, 
	lab.lms_pv1_pv1_19_cx_1, 
	CRMAF_lms_lms_result.lms_spm_spm_2_eip_2_ei_1 ASC, 
	CRMAF_lms_lms_result.lms_spm_spm_2_eip_1_ei_1 ASC, 
	CRMAF_lms_lms_result.lms_obx_obx_3_cwe_1
	) PCR_INNER
WHERE TEST_DATE_ORDER = '1'
GROUP BY Lab_Name, Lab_Accession_NR, LabSpecimenNumber, FieldSpecimenNumber, SpecTypeCode, TestDate, TestInterp
) PCR



