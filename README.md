# Mediscreen
Mediscreen is an application used by doctors to detect patients at risk of developing diabetes.  
From some information (like age, sex...) and the historic (notes) of the patient, this application can generate
a report. 4 risk levels are used:
* none
* borderline
* in danger
* early onset

ms_demography is a microservice used by Mediscreen.  
ms_report is the main microservice of Mediscreen application (https://github.com/ChocolateSquirrel/ms_report/tree/dev).  