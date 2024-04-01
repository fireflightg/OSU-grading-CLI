#!/usr/bin/env node

import { rimraf, rimrafSync, native, nativeSync } from 'rimraf'
import chalk from 'chalk';
import boxen from 'boxen';
import figlet from 'figlet';
import yargs from 'yargs/yargs';
import { hideBin } from 'yargs/helpers';
import shell from 'shelljs';
import cliProgress from 'cli-progress';



import path from 'path';

import AdmZip from 'adm-zip';



import { execSync } from 'child_process';
import https from 'https';
import fsp from "fs/promises"
import fs from 'fs';
import readline from "readline"
  
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
const apiKey = "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";


import OpenAI from 'openai';

const openai = new OpenAI({ apiKey });




async function scoreJUnitTests(junitPath, projectNumber) {
    const rubric = testrubric.getrubric(projectNumber); // Make sure this function returns the actual rubric details
    const testFiles = fs.readdirSync(testDir).filter(file => file.endsWith('.java'));

    const fileContent = await fsp.readFile(junitPath, 'utf8');

    const prompt = `Review the following JUnit test code to determine if it covers these required aspects based on the rubric. Assign a score out of 10 based on the coverage.\n\nRubric:\n${rubric}\n\nJUnit Test Code:\n${fileContent}`;

    try {
        const response = await openai.chat.completions.create({
            model: "gpt-3.5-turbo",
            prompt: prompt,
            max_tokens: 1000
        });

        const gptResponse = response.data.choices[0].text.trim();
        console.log("GPT Response:", gptResponse);

        // Extract the score from GPT's response
        const scoreMatch = gptResponse.match(/score: (\d+(\.\d+)?)/i);
        const score = scoreMatch ? parseFloat(scoreMatch[1]) : 0;

        console.log(`Score for ${path.basename(junitPath)}: ${score} out of 10`);
        return score;
    } catch (error) {
        console.error("Error:", error);
        return 0;
    }

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

    // Define directories
    const buildDir = 'build'; // Adjust as needed

    const srcDir = './src'; // Adjust as needed
    const testDir = `./osutests/${projectnum}`; // Adjust as needed
    const junitJar = `./lib/junit-4.12.jar`;
    const hamcrestJar = `./lib/hamcrest-core-1.3.jar`;
    const osuComponentJar = `./lib/osu-component.jar`;

    // Compile module source files
    execSync(`javac -d "${buildDir}" -cp "${junitJar}:${hamcrestJar}:${osuComponentJar}" "${srcDir}/${moduleDir}"/*.java`);

    // Read all test files
    const testFiles = fs.readdirSync(testDir).filter(file => file.endsWith('.java'));
    for (const testFile of testFiles) {
        const testClass = path.basename(testFile, '.java');
        console.log(`Compiling test file: ${testClass}`);
        
        // Compile test file
        execSync(`javac -d "${buildDir}" -cp "${junitJar}:${hamcrestJar}:${osuComponentJar}:${buildDir}" "${testDir}/${testFile}"`);

        console.log(`Running test for ${testClass}...`);
        
        // Run the compiled test class and capture the output
        try {
            var testOutput = execSync(`java -cp "./lib/junit-4.12.jar:./lib/hamcrest-core-1.3.jar:./lib/osu-component.jar:build" org.junit.runner.JUnitCore ${testClass}`, { encoding: 'utf-8' });
            formatTestOutput(testClass, "Passed All",moduleDir);
        } catch (e) {
            console.error(`Error running tests for NaturalNumber3Grade:`);
            //console.error('STDOUT:', e.stdout);
            formatTestOutput(testClass, e.stdout, moduleDir);
           
        }

        // Display the test output
     
    }
}

function formatTestOutput(testClass, output, group) {
 
    //var ind = output.indexOf("Failures:");
     console.log(`Test results for ${group}(${testClass}):`);
     if(output === "Passed All"){
    console.log(chalk.greenBright(output+" 🤝"));
     }
     else{
        var index = output.indexOf("Failures:");
        var total = output.indexOf("Failures:");
      var totalerrors =output.substring(total-5 );
       var errors = Number(output.substring(index+9,output.length));
       for(var num = 1; num<=errors; num++ ){
       var arr = output.split(/at /);
       for(const j in arr){
        if(arr[j].includes(num+") ")){
            console.log(chalk.bgRedBright( chalk.bold("ERRORS 😡")));
console.log(chalk.redBright(arr[j].substring(num+") ",arr[j].length-1)));

if(arr[j+1] != undefined){
console.log(chalk.redBright(arr[j+1].substring(num+") ",arr[j+1].length-1)));
}

        }
       }
     
       
        
       }
       console.log(chalk.yellow("Total Tests: "+totalerrors));
        //console.log(chalk.red(output.substring(output.indexOf("")))); 
     }
    //var strings = output.substring();
    

   
}

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
                const testFiles = fs.readdirSync(moduleDir).filter(file => file.endsWith('.java'));

                for (const testFile of testFiles) {
                    const fullPath = path.join(moduleDir, testFile);
                    if (fs.statSync(fullPath).isFile()) {
                        // Ensure this function is also properly handling asynchronous operations
                        await scoreJUnitTests(fullPath, projectnum)
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
  function downloadFile(url, destination) {
      return new Promise((resolve, reject) => {
          const file = fs.createWriteStream(destination);
          https.get(url, response => {
              response.pipe(file);
              file.on('finish', () => {
                  file.close(resolve);
              });
          }).on('error', err => {
              fs.unlink(destination, () => reject(err));
          });
      });
  }
  
  // Function to download dependencies
  async function downloadDependencies() {
      console.log("Downloading dependencies...");
  
      if (!fs.existsSync(LIB_DIR)){
          fs.mkdirSync(LIB_DIR);
      }
  
      if (!fs.existsSync(BUILD_DIR)){
          fs.mkdirSync(BUILD_DIR);
      }
  
      await downloadFile(JUNIT_JAR_URL, `${LIB_DIR}/junit-4.12.jar`);
      await downloadFile(HAMCREST_CORE_URL, `${LIB_DIR}/hamcrest-core-1.3.jar`);
      await downloadFile(OSU_COMPONENT_URL, `${LIB_DIR}/osu-component.jar`);
  }
  
  // Function to compile and run tests
 
  

 
  
 
  
  