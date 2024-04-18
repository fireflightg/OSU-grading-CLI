#!/usr/bin/env node

import { rimraf, rimrafSync, native, nativeSync } from 'rimraf'
import chalk from 'chalk';
import boxen from 'boxen';
import figlet from 'figlet';
import yargs from 'yargs/yargs';
import { hideBin } from 'yargs/helpers';
import shell from 'shelljs';
import cliProgress from 'cli-progress';
import { GoogleGenerativeAI } from "@google/generative-ai";
import { PDFDocument } from 'pdf-lib';
import pdf from 'pdf-parse';

import dotenv from "dotenv";
import path from 'path';

import AdmZip from 'adm-zip';



import { execSync } from 'child_process';
import https from 'https';
import fsp from "fs/promises"
import fs from 'fs';
import readline from "readline"

import axios from 'axios';




  
  // Directories
  const LIB_DIR = './lib';
  const SRC_DIR = './src';
  const TEST_DIR = './test';
  const BUILD_DIR = './build';
  
  // Dependency URLs
  const JUNIT_JAR_URL = 'https://search.maven.org/remotecontent?filepath=junit/junit/4.12/junit-4.12.jar';
  const HAMCREST_CORE_URL = 'https://search.maven.org/remotecontent?filepath=org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar';
  const OSU_COMPONENT_URL = 'https://web.cse.ohio-state.edu/software/common/components.jar';

// Usage string with styling

const usa = chalk.greenBright("\nUsage: grad -n <project number> -t <porject type>\n")
  + boxen(chalk.cyanBright("\nGrade projects in seconds\n"), {padding: 1, borderColor: 'cyanBright', dimBorder: true}) + "\n";

  const argv = yargs(hideBin(process.argv))
  .usage(usa)
  .option("n", { alias: "number", describe: "The number of the project being graded", type: "string" })
  .option("t", { alias: "type", describe: "Type of grading (test or main file)", type: "string" })
  .option("i", { alias: "install", describe: "Install dependencies", type: "boolean" })
  .option("d", { alias: "delete", describe: "Clears folders", type: "boolean" })
  .option("l", { alias: "load", describe: "Sets up zip files for grading", type: "string" })
  .option("r", { alias: "run", describe: "Run the grading process", type: "boolean" })
  .option("a", { alias: "addtest", describe: "adds a new test case", type: "string" })
  .help(true)
  .parse();
  if (!argv.number && !argv.type) {
    console.log(chalk.greenBright(figlet.textSync('OSU GRADER', { horizontalLayout: 'full' })));
    console.log(usa);
    yargs().showHelp();
    
}
if (argv.install) {
  downloadDependencies();
}

if (argv.delete) {
  shell.rm('-rf', './src/*');
  shell.rm('-rf', './test/*');
}

if (argv.load) {
  extractZipFiles(argv.load);
}
const srcDir = 'src';
const testDir = 'test';
if (argv.number && argv.type) {

    runtester(argv.number, argv.type);
}
  
if (argv.addtest) {
testrubric.addrubric(argv.addtest)
}
//import brain from 'brain.js';

// Initialize a new neural network instance
//const network = new brain.recurrent.LSTM();

// Example training data
// const trainingData = [
//     { input: 'hello', output: 'greeting' },
//     { input: 'hi', output: 'greeting' },
//     { input: 'hey', output: 'greeting' },
//     { input: 'goodbye', output: 'farewell' },
//     { input: 'bye', output: 'farewell' },
//     { input: 'see you', output: 'farewell' }
// ];

 
// Train the network
// network.train(trainingData, {
//     iterations: 2000, // the maximum times to iterate the training data
//     errorThresh: 0.005, // the acceptable error percentage from training data
// });

// Your OpenAI API key (replace "your-api-key" with your actual API key)
;









async function scoretests(junitPath, projectnum){
    const rubriclocation = `./osutests/${projectnum}/rubric`;
   
   // const testFiles = fs.readdirSync(testDir).filter(file => file.endsWith('.java'));
    const rubrics = fs.readdirSync(rubriclocation).filter(file => file.endsWith('.pdf'));
    console.log(rubrics);

    const fileContent = await fsp.readFile(junitPath, 'utf8');
    const dataBuffer = await fsp.readFile(`${rubriclocation}/${rubrics[0]}`);
    try {
        const data = await pdf(dataBuffer);
        dotenv.config();

        const genAI = new GoogleGenerativeAI("AIzaSyCV8-MEg_NrWxT2n9kYJyI1oHATg1Mut08");
        
        // ...
        
        const model = genAI.getGenerativeModel({ model: "gemini-pro"});
        const prompt = `using this scoring rubric: ${data.text.substring(data.text.indexOf("JUNIT"))} , give this students java test file: ${fileContent}, a score out of 10 in the format of:
        To create a consistent scoring rubric prompt for grading Java test files based on three separate JUnit rubrics you uploaded, here's a template that you can adapt for each specific project's criteria:

---

**Grading Prompt Template for Java Test Files**

**Introduction:**
Using the rubric provided for the specific project, grade the student's Java test file out of a total of 10 points. The grading will be based solely on the criteria outlined in the JUnit rubric to ensure consistency.

**Scoring Format:**


If the score is not perfect, provide detailed feedback as follows:

**Reasons for Losing Points:**
- (-X points): [Specific reason for deduction according to the rubric]
  
**How and Where to Fix the Problems:**
1. **[Location/File/Method where the issue occurs]** - [Detailed instruction on how to fix the issue].

**Grading Criteria:**
- Follow the rubric meticulously to assess both implementation correctness and completeness of the JUnit test cases.
- Deduct points for missing features, bugs, or deviation from the expected solutions as outlined in the rubric.
- Use emojis to visually represent the severity or nature of the issue 🐞🔍.

**Final Steps:**
- Ensure that all deductions are accurately reflected in the final score.
- Revisit each deduction to confirm it aligns with the rubric's specifics.

**Example for a Specific Project Rubric (e.g., Project 2 Rubric):**
- Implementation issues such as incorrect handling of edge cases or unnecessary complexity.
- Missing or incomplete test cases.
- Specific penalties for misusing or not implementing methods correctly.



Score: #/10

Remember, the specifics of what to check for in the code and tests, such as handling edge cases, structuring tests, and method implementations, will depend on the detailed directives from the respective project's rubric.
`
        const result =await  model.generateContent(prompt);
        const response = await result.response;
        const text = response.text();
        console.log(text.includes("10/10")?chalk.green(text):chalk.red(text));
      } catch (error) {
        console.error("Error extracting PDF text:", error);
      }


   

// ...
}


// Example usage
const testrubric = {
getrubric: function getrubric(num){
return [
    "Ensure the default constructor properly initializes the object, representing the abstract state correctly.",
    "Test the integer constructor with zero to verify it sets the object's state appropriately.",
    "Test the integer constructor with a non-zero value to ensure it correctly sets the object's state.",
    "Verify that the string constructor with '0' correctly initializes the object's state.",
    "Ensure the string constructor with a non-zero string properly sets the object's state.",
    "Test the NaturalNumber constructor with a zero value to check it correctly initializes the object's state.",
    "Verify the NaturalNumber constructor with a non-zero value accurately sets the object's state.",
    "Test the multiplyBy10 method with zero to confirm it handles this case correctly without altering the object's state.",
    "Check the multiplyBy10 method with a non-zero argument to ensure it correctly alters the object's state.",
    "Verify the divideBy10 method with the object at zero properly handles this case and does not change the object's state.",
    "Test the divideBy10 method with a non-zero object to ensure it accurately performs the division and alters the state correctly.",
    "Ensure the isZero method returns true when the object's state represents zero.",
    "Verify the isZero method returns false when the object's state is non-zero."
].join("\n");


},
addrubric: (text)=> {
text.split(",");


}
}


 
  
  


function compileAndRunTests(moduleDir, projectnum) {
    console.log(chalk.bgCyanBright(`Compiling and testing group: ${chalk.whiteBright(moduleDir)}`));

    const buildDir = './build';
    const srcDir = `./src/${moduleDir}`;
    const testDir = `./osutests/${projectnum}`;
    const programTestDir = `./osutests/${projectnum}/test`; // Adjust if ProgramTest.java is in a different location
    const junitJar = `./lib/junit-4.12.jar`;
    const hamcrestJar = `./lib/hamcrest-core-1.3.jar`;
    const osuComponentJar = `./lib/osu-component.jar`;

    // Ensure the build directory exists
    if (!fs.existsSync(buildDir)) {
        fs.mkdirSync(buildDir, { recursive: true });
    }

    // Compile ProgramTest.java if it exists
    try{
    const gradingtest = fs.readdirSync(programTestDir).filter(file => file.endsWith('.java'));
for(const testingvar in gradingtest){
    
        console.log(`Compiling ${testingvar}.java`);
        execSync(`javac -d "${buildDir}" -cp "${junitJar}:${hamcrestJar}:${osuComponentJar}" "${programTestDir}/${gradingtest[testingvar]}"`);
   
}
}
catch{
    console.log(`no additional test files found`);
}

    // Compile module source files
    execSync(`javac -d "${buildDir}" -cp "${junitJar}:${hamcrestJar}:${osuComponentJar}" "${srcDir}"/*.java`);

    // Read all test files
    const testFiles = fs.readdirSync(testDir).filter(file => file.endsWith('.java'));
    for (const testFile of testFiles) {
        const testClass = path.basename(testFile, '.java');
        console.log(`Compiling test file: ${testClass}`);
        
        // Compile test file
        execSync(`javac -d "${buildDir}" -cp "${junitJar}:${hamcrestJar}:${osuComponentJar}:${buildDir}" "${testDir}/${testFile}"`);

        console.log(`Running test for ${testClass}...`);
        
        try {
            var testOutput = execSync(`java -cp "${junitJar}:${hamcrestJar}:${osuComponentJar}:${buildDir}" org.junit.runner.JUnitCore ${testClass}`, { encoding: 'utf-8' });
            formatTestOutput(testClass, "Passed All", moduleDir);
        } catch (e) {
            console.error(`Error running tests for ${testClass}:`);
            formatTestOutput(testClass, e.stdout, moduleDir);
        }
    }
}

function formatTestOutput(testClass, output, group) {
    console.log(`Test results for ${group}(${testClass}):`);
    if (output === "Passed All") {
        console.log(chalk.greenBright(output + " 🤝"));
    } else {
        var index = output.indexOf("Failures:");
        var total = output.indexOf("Failures:");
        var totalerrors = output.substring(total - 5);
        var errors = Number(output.substring(index + 9, output.length));
        for (var num = 1; num <= errors; num++) {
            var arr = output.split(/at /);
            for (const j in arr) {
                if (arr[j].includes(num + ") ")) {
                    console.log(chalk.bgRedBright(chalk.bold("ERRORS 😡")));
                    // console.log(chalk.redBright(arr[j].substring(num + ") ", arr[j].length - 1)));
                    console.log(chalk.redBright(arr[j]));

                    if (arr[j + 1] != undefined) {
                        console.log(chalk.redBright(arr[j]));
                        //console.log(chalk.redBright(arr[j + 1].substring(num + ") ", arr[j + 1].length - 1)));
                    }
                }
            }
        }
        console.log(chalk.yellow("Total Tests: " + totalerrors));
    }
}

// Call the function with the appropriate module directory and project number


// function compileAndRunTests(moduleDir, projectnum) {
   
//     console.log(chalk.bgCyanBright(`Compiling and testing group: ${chalk.whiteBright(moduleDir)}`));

//     // Define directories
//     const buildDir = 'build'; // Adjust as needed

//     const srcDir = './src'; // Adjust as needed
//     const testDir = `./osutests/${projectnum}`; // Adjust as needed
//     const junitJar = `./lib/junit-4.12.jar`;
//     const hamcrestJar = `./lib/hamcrest-core-1.3.jar`;
//     const osuComponentJar = `./lib/osu-component.jar`;

//     // Compile module source files
//     execSync(`javac -d "${buildDir}" -cp "${junitJar}:${hamcrestJar}:${osuComponentJar}" "${srcDir}/${moduleDir}"/*.java`);

//     // Read all test files
//     const testFiles = fs.readdirSync(testDir).filter(file => file.endsWith('.java'));
//     for (const testFile of testFiles) {
//         const testClass = path.basename(testFile, '.java');
//         console.log(`Compiling test file: ${testClass}`);
        
//         // Compile test file
//         execSync(`javac -d "${buildDir}" -cp "${junitJar}:${hamcrestJar}:${osuComponentJar}:${buildDir}:./osutests/7/ProgramTest.java" "${testDir}/${testFile}"`);

//         console.log(`Running test for ${testClass}...`);
        
//         // Run the compiled test class and capture the output
        
//         try {
//             var testOutput = execSync(`java -cp "./lib/junit-4.12.jar:./lib/hamcrest-core-1.3.jar:./lib/osu-component.jar:build:./lib/component.jar:build" org.junit.runner.JUnitCore ${testClass}`, { encoding: 'utf-8' });
//             formatTestOutput(testClass, "Passed All",moduleDir);
//         } catch (e) {
//             console.error(`Error running tests for NaturalNumber3Grade:`);
//             //console.error('STDOUT:', e.stdout);
//             formatTestOutput(testClass, e.stdout, moduleDir);
           
//         }

//         // Display the test output
     
//     }
// }

// function formatTestOutput(testClass, output, group) {
 
//     //var ind = output.indexOf("Failures:");
//      console.log(`Test results for ${group}(${testClass}):`);
//      if(output === "Passed All"){
//     console.log(chalk.greenBright(output+" 🤝"));
//      }
//      else{
//         var index = output.indexOf("Failures:");
//         var total = output.indexOf("Failures:");
//       var totalerrors =output.substring(total-5 );
//        var errors = Number(output.substring(index+9,output.length));
//        for(var num = 1; num<=errors; num++ ){
//        var arr = output.split(/at /);
//        for(const j in arr){
//         if(arr[j].includes(num+") ")){
//             console.log(chalk.bgRedBright( chalk.bold("ERRORS 😡")));
// console.log(chalk.redBright(arr[j].substring(num+") ",arr[j].length-1)));

// if(arr[j+1] != undefined){
// console.log(chalk.redBright(arr[j+1].substring(num+") ",arr[j+1].length-1)));
// }

//         }
//        }
     
       
        
//        }
//        console.log(chalk.yellow("Total Tests: "+totalerrors));
//         //console.log(chalk.red(output.substring(output.indexOf("")))); 
//      }
//     //var strings = output.substring();
    

   
// }

// Usage
 // Adjust as needed


function runtester(projectnum, type){
    console.log(type);

    if(type.includes("m")){
    fs.readdir(srcDir, { withFileTypes: true }, (err, entries) => {
        if (err) {
            console.error('Error reading the source directory:', err);
            return;
        }
    
        for (const entry of entries) {
            if (entry.isDirectory()) {
                const moduleDir = path.join(srcDir, entry.name);
                compileAndRunTests(entry.name,projectnum);
            }
        }
    });
}

if (type.includes("t")) {
    fs.readdir(testDir, { withFileTypes: true }, async (err, entries) => {
        if (err) {
            console.error('Error reading the test directory:', err);
            return;
        }

        for (const entry of entries) {
            if (entry.isDirectory()) {
                const moduleDir = path.join(testDir, entry.name);
                const testFiles = fs.readdirSync(moduleDir).filter(file => file.endsWith('.java')&& !/\d/.test(file));
                console.log(testFiles);

                for (const testFile of testFiles) {
                    const fullPath = path.join(moduleDir, testFile);
                    if (fs.statSync(fullPath).isFile()) {
                        // Ensure this function is also properly handling asynchronous operations
                        console.log(chalk.bgCyanBright(`grading group ${entry.name} test file`));
                        await scoretests(fullPath, projectnum)
                    }
                }
            }
        }
    });

    }
}
   












 





 function extractZipFiles(projectnumbers){
    

    const bar1 = new cliProgress.SingleBar({}, cliProgress.Presets.shades_classic);
    const zip = new AdmZip(`./submissions${projectnumbers}.zip`);
    var zipEntries = zip.getEntries();
   
    console.log("finding projects... 😭");

    var count  = 0;

    bar1.start(0);
    zipEntries.forEach(function (zipEntry) {
        
        if (zipEntry.entryName.indexOf(".zip")> -1) {
            console.log("\n"+"zip found 👀");
            count++;
            bar1.setTotal(count*100);
            zip.extractEntryTo(zipEntry.entryName, "./source", false,  true);
            var groupnum = zipEntry.entryName.substring(0,2);

            var innerzip = new AdmZip("./source/"+zipEntry.entryName);  
            var inner = innerzip.getEntries()  
            inner.forEach(function (zipEntri){
                
                if (zipEntri.entryName.indexOf("src/")> -1 &&zipEntri.entryName.indexOf(".java")> -1 ) {
                   
                    innerzip.extractEntryTo(/*entry name*/zipEntri.entryName , /*target path*/ "./src/"+groupnum, /*maintainEntryPath*/ false, /*overwrite*/true);
                   
                }
                if (zipEntri.entryName.indexOf("test/")> -1 &&zipEntri.entryName.indexOf(".java")> -1 ) {
                    innerzip.extractEntryTo(/*entry name*/zipEntri.entryName , /*target path*/ "./test/"+groupnum, /*maintainEntryPath*/ false, /*overwrite*/true);
                    //console.log(zipEntri.entryName); 
                  

                }

            });

        }
        bar1.update(100);

    });

   bar1.stop();
   
    
    
}




const sourceFolder = './source';








      




  // Function to download a file

  
  // Function to download dependencies

function ensureDirectoryExists(directory) {
    if (!fs.existsSync(directory)) {
        fs.mkdirSync(directory, { recursive: true });
    }
}

async function downloadFile(url, destination) {
    const writer = fs.createWriteStream(destination);

    const response = await axios({
        url,
        method: 'GET',
        responseType: 'stream',
        headers: {
            'User-Agent': 'Mozilla/5.0' // Set a user agent if necessary
        }
    });

    response.data.pipe(writer);

    return new Promise((resolve, reject) => {
        writer.on('finish', resolve);
        writer.on('error', reject);
    });
}

async function downloadDependencies() {
    ensureDirectoryExists(LIB_DIR);
    ensureDirectoryExists(BUILD_DIR);

    console.log("Downloading dependencies...");

    try {
        await downloadFile(JUNIT_JAR_URL, path.join(LIB_DIR, 'junit-4.12.jar'));
        await downloadFile(HAMCREST_CORE_URL, path.join(LIB_DIR, 'hamcrest-core-1.3.jar'));
        await downloadFile(OSU_COMPONENT_URL, path.join(LIB_DIR, 'osu-component.jar'));
        console.log("Dependencies downloaded successfully.");
    } catch (error) {
        console.error("Error downloading dependencies:", error);
    }
}



  
  
  // Function to compile and run tests
 
  

 
  
 
  
  