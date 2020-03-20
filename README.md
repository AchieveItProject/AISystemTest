# Software Reconstruction Tool - Server
Tools for software reconstruction (namely refactoring) during the design phase.

## Target


<div style="float:left;"> 
<img src="https://img.shields.io/badge/deploy-success-brightgreen.svg" alt="Build Status">  
<img src="https://img.shields.io/badge/version-1.0-green.svg" alt="Version">
<img src="https://img.shields.io/badge/license-MIT-red.svg" alt="License">
</div>


## Introduction

Page preview: Unavailable.

Tool aims to provide a solution for the reconstruction of design class diagrams, and realize the transformation tool from SysML diagrams to component diagrams, so as to better analyze user needs and reduce design standards.

Both static export (including PDF and SVG formats) and dynamic support (web and mobile access) is supported.

Warranty is supported according to Github. If bugs are reported, fixing and strengthening this project is possible if time's allowed. To report the problem, please open (or create) an issue at this project on the GitHub. Contact us by [email](10165101169@stu.ecnu.edu.cn) if eaging to get response.


## Future

- [ ] Upload languages can be expanded from SysML to UML, and more.

- [ ] Since different softwares provide different encoding formats of modeling language, we will try to compatitable for more softwares. 

- [ ] Algorithm for refactoring may change and optimize later. For better experience, we'll keep both versions (for users to choose) until stable version is published.

## Technology Stack

Website: vue2 + vue-router + npm + ES6 + ajax + json + redis

Server: Jama + graphviz (static output) + Spring Boot

Test Framework: JUnit + Mockito + Hamcrest + Coverage Runner (reports will be supported in the future)

For the website part of this project, see [here](github.com/ivyee17/software-reconstruction-website).

## Deployment

1. Open redis with localhost:6387
2. Open mysql with table username (defined in dao.user)
3. Make sure server port 8091 is not used by other apps
4. Load SpringBoot with Java
5. Enjoy!

## References

The refactoring algorithm is based on <i>S. Cao, Y. Zhao, and L. Shi, “Software Complexity Reduction by Automated Refactoring Schema.”</i>

The formula of matrix's energy is from <i>K. Sinha and O. L. De Weck, “A network-based structural complexity metric for engineered complex systems,” SysCon 2013 - 7th Annu. IEEE Int. Syst. Conf. Proc., no. September 2016, pp. 426–430, 2013.</i>

## Copyright

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software.

If used for course homework or commercial situation, it should subject to the following conditions: Be sure to assert the author's name and details in the project with obvious position.

For the detail, read the complete [copyright](./LICENSE) here.
