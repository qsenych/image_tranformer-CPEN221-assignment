# MP1: IMAGE PROCESSING FTW! (Feedback)

## Grade: A+ = 10/10

**Compiles**: Yes:Yes:Yes:Yes:Yes

**Timeout**: No:No:No:No:No

## Test Results
### ca.ubc.ece.cpen221.ip.mp.Mirroring
| Test Status | Count |
| ----------- | ----- |
|tests|5|
|skipped|0|
|failures|0|
|errors|0|
### ca.ubc.ece.cpen221.ip.mp.Negative
| Test Status | Count |
| ----------- | ----- |
|tests|3|
|skipped|0|
|failures|0|
|errors|0|
### ca.ubc.ece.cpen221.ip.mp.Posterize
| Test Status | Count |
| ----------- | ----- |
|tests|7|
|skipped|0|
|failures|0|
|errors|0|

### ca.ubc.ece.cpen221.ip.mp.Denoising
| Test Status | Count |
| ----------- | ----- |
|tests|3|
|skipped|0|
|failures|0|
|errors|0|
### ca.ubc.ece.cpen221.ip.mp.Weathering
| Test Status | Count |
| ----------- | ----- |
|tests|5|
|skipped|0|
|failures|0|
|errors|0|
### ca.ubc.ece.cpen221.ip.mp.BlockPainting
| Test Status | Count |
| ----------- | ----- |
|tests|3|
|skipped|0|
|failures|0|
|errors|0|

### ca.ubc.ece.cpen221.ip.mp.Similarity
| Test Status | Count |
| ----------- | ----- |
|tests|6|
|skipped|0|
|failures|0|
|errors|0|
### ca.ubc.ece.cpen221.ip.mp.BestMatch
| Test Status | Count |
| ----------- | ----- |
|tests|5|
|skipped|0|
|failures|0|
|errors|0|

### ca.ubc.ece.cpen221.ip.mp.GreenScreen
| Test Status | Count |
| ----------- | ----- |
|tests|7|
|skipped|0|
|failures|0|
|errors|0|

### ca.ubc.ece.cpen221.ip.mp.TextAlignment
| Test Status | Count |
| ----------- | ----- |
|tests|8|
|skipped|0|
|failures|1|
|errors|0|
#### Failed Tests
1. `testEmptyImage() (org.opentest4j.AssertionFailedError: Expected angle was 0 but got -87 ==> expected: <true> but was: <false>)`

## Code Coverage
### ImageDFT
| Metric | Coverage |
| ------ | -------- |
|LINE_MISSED|4|
|LINE_COVERED|101|
|BRANCH_MISSED|4|
|BRANCH_COVERED|58|
### ImageSimilarity
| Metric | Coverage |
| ------ | -------- |
|LINE_MISSED|0|
|LINE_COVERED|6|
|BRANCH_MISSED|0|
|BRANCH_COVERED|0|
### ColourRegion
| Metric | Coverage |
| ------ | -------- |
|LINE_MISSED|2|
|LINE_COVERED|11|
|BRANCH_MISSED|12|
|BRANCH_COVERED|4|
### ImageTransformer
| Metric | Coverage |
| ------ | -------- |
|LINE_MISSED|32|
|LINE_COVERED|205|
|BRANCH_MISSED|24|
|BRANCH_COVERED|130|
### Main
| Metric | Coverage |
| ------ | -------- |
|LINE_MISSED|15|
|LINE_COVERED|0|
|BRANCH_MISSED|0|
|BRANCH_COVERED|0|
### Complex
| Metric | Coverage |
| ------ | -------- |
|LINE_MISSED|2|
|LINE_COVERED|11|
|BRANCH_MISSED|0|
|BRANCH_COVERED|0|
### ImageProcessing
| Metric | Coverage |
| ------ | -------- |
|LINE_MISSED|3|
|LINE_COVERED|52|
|BRANCH_MISSED|4|
|BRANCH_COVERED|28|
### Image
| Metric | Coverage |
| ------ | -------- |
|LINE_MISSED|119|
|LINE_COVERED|63|
|BRANCH_MISSED|58|
|BRANCH_COVERED|26|
### ImageProcessingException
| Metric | Coverage |
| ------ | -------- |
|LINE_MISSED|1|
|LINE_COVERED|0|
|BRANCH_MISSED|0|
|BRANCH_COVERED|0|
### Rectangle
| Metric | Coverage |
| ------ | -------- |
|LINE_MISSED|1|
|LINE_COVERED|7|
|BRANCH_MISSED|4|
|BRANCH_COVERED|4|
### DoubleMatrix
| Metric | Coverage |
| ------ | -------- |
|LINE_MISSED|25|
|LINE_COVERED|0|
|BRANCH_MISSED|18|
|BRANCH_COVERED|0|

## Comments
 The code demonstrates good modularity and clear structure, but some methods are lengthy and could benefit from further decomposition for improved readability  Great effort on implementing complex image processing techniques, keep exploring ways to enhance your code organization!
### Overall
- Generally great specifications! I also appreciate the Representation Invariant and Abstraction Function in some of the files
- Great code style! Splitting up functionality across various files, especially collections of utility functions, allows for much easier readability and maintainability.
- Code overall is quite descriptive with good variable names and good use of in-code comments where necessary. Some methods could be improved with some comments for clarity, such as `bfs`, `fft`, and `fftShift`.
- Remember not to submit files that aren't necessary, such as `Main.java`

### `Complex.java`

- **Specifications**: The specifications are clear and concise. They effectively describe the purpose and behavior of each method.
- **Code Style**: The code follows a consistent style, making it easy to read and understand. The use of `this` is consistent and appropriate.
- **Method Size and Readability**: The methods are small and focused on a single task, which enhances readability and maintainability.
- **Suggestions**: Consider adding a `toString` method for better debugging and logging capabilities.

### `ColourRegion.java`

- **Specifications**: The specifications are clear, but the class-level documentation could be improved. The abstraction function and representation invariant are well-defined, but they should be included in a more formal specification comment.
- **Code Style**: The code is generally well-structured, but the use of underscores in parameter names (e.g., `_xTopLeft`) is unconventional in Java and should be avoided.
- **Method Size and Readability**: The constructor and methods are concise and focused, which is good for readability.
- **Suggestions**: Consider using private fields with public getters if you want to maintain encapsulation.

### `ImageDFT.java`

- **Specifications**: The specifications are generally clear, but some methods could benefit from more detailed explanations, especially those involving complex mathematical operations.
- **Code Style**: The code is logically organized, but some methods are quite long and could be broken down into smaller helper methods for better readability.
- **Method Size and Readability**: The `fft2D`, `postprocess`, and `houghTransform` methods are lengthy and complex. Breaking them into smaller methods would improve readability.
- **Suggestions**: Consider adding comments within complex methods to explain the logic, especially for non-trivial algorithms like FFT.

### `ImageProcessing.java`

- **Specifications**: The specifications are adequate but could be expanded to explain edge cases and assumptions, such as what happens when images are of different sizes.
- **Code Style**: The code is mostly clean, but the use of `BigInteger` for summing pixel values seems excessive and could be replaced with simpler data types unless there's a specific reason for its use.
- **Method Size and Readability**: The `cosineSimilarity` method is quite long and could be split into smaller methods for clarity. The use of descriptive variable names would also enhance readability.
- **Suggestions**: Consider refactoring the `cosineSimilarity` method to improve readability and reduce complexity.

### `ImageSimilarity.java`

- **Specifications**: The specifications are minimal but sufficient for the simplicity of the class. However, they could be expanded to explain the purpose and use cases of the class.
- **Code Style**: The code is straightforward and follows Java conventions.
- **Method Size and Readability**: The class is simple, and the methods are appropriately sized.
- **Suggestions**: Consider adding a `toString` method for better debugging and logging capabilities.

### `ImageTransformer.java`

- **Specifications**: The class-level specification is incomplete, as it mentions operations without listing them. Method-level specifications are generally clear but could be more detailed in some cases.
- **Code Style**: The code is mostly well-organized, but some methods are quite long and could benefit from refactoring. The use of magic numbers (e.g. '64', '96', and other numbers in `posterize`) should be replaced with named constants for clarity.
- **Method Size and Readability**: Some methods, such as `denoise` and `weather`, are lengthy and complex. Breaking them into smaller helper methods would improve readability, especially for duplicated functionality such as the nested for-loops to get neighbouring pixels
- **Suggestions**: Consider refactoring methods to reduce complexity and improve readability. Ensure all public methods have comprehensive specifications, including edge cases and assumptions.


**Detailed Code Analysis**

| Filename | Line | Issue | Explanation |
| -------- | ---- | ----- | ----------- |
|ColourRegion.java|3|	UnusedImports|	Unused import 'ca.ubc.ece.cpen221.ip.core.Rectangle'|
|ColourRegion.java|12|	BeanMembersShouldSerialize|	Found non-transient, non-static member. Please mark as transient or provide accessors.|
|ColourRegion.java|12|	BeanMembersShouldSerialize|	Found non-transient, non-static member. Please mark as transient or provide accessors.|
|ColourRegion.java|12|	OneDeclarationPerLine|	Use one line for each declaration, it enhances code readability.|
|ColourRegion.java|13|	BeanMembersShouldSerialize|	Found non-transient, non-static member. Please mark as transient or provide accessors.|
|ColourRegion.java|13|	BeanMembersShouldSerialize|	Found non-transient, non-static member. Please mark as transient or provide accessors.|
|ColourRegion.java|13|	OneDeclarationPerLine|	Use one line for each declaration, it enhances code readability.|
|ColourRegion.java|14|	BeanMembersShouldSerialize|	Found non-transient, non-static member. Please mark as transient or provide accessors.|
|ColourRegion.java|15|	BeanMembersShouldSerialize|	Found non-transient, non-static member. Please mark as transient or provide accessors.|
|ColourRegion.java|17|	BeanMembersShouldSerialize|	Found non-transient, non-static member. Please mark as transient or provide accessors.|
|ColourRegion.java|18|	BeanMembersShouldSerialize|	Found non-transient, non-static member. Please mark as transient or provide accessors.|
|ImageDFT.java|19|	BeanMembersShouldSerialize|	Found non-transient, non-static member. Please mark as transient or provide accessors.|
|ImageDFT.java|21|	BeanMembersShouldSerialize|	Found non-transient, non-static member. Please mark as transient or provide accessors.|
|ImageDFT.java|22|	BeanMembersShouldSerialize|	Found non-transient, non-static member. Please mark as transient or provide accessors.|
|ImageDFT.java|78|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'imageMatrix' (lines '78'-'86').|
|ImageDFT.java|78|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'imageMatrix' (lines '78'-'88').|
|ImageDFT.java|78|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'imageMatrix' (lines '78'-'91').|
|ImageDFT.java|84|	LawOfDemeter|	Potential violation of Law of Demeter (object not created locally)|
|ImageDFT.java|86|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'imageMatrix' (lines '86'-'86').|
|ImageDFT.java|86|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'imageMatrix' (lines '86'-'88').|
|ImageDFT.java|86|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'imageMatrix' (lines '86'-'91').|
|ImageDFT.java|88|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'imageMatrix' (lines '88'-'86').|
|ImageDFT.java|88|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'imageMatrix' (lines '88'-'88').|
|ImageDFT.java|88|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'imageMatrix' (lines '88'-'91').|
|ImageDFT.java|91|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'imageMatrix' (lines '91'-'86').|
|ImageDFT.java|91|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'imageMatrix' (lines '91'-'88').|
|ImageDFT.java|91|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'imageMatrix' (lines '91'-'91').|
|ImageDFT.java|111|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'height' (lines '111'-'128').|
|ImageDFT.java|113|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'maxMag' (lines '113'-'128').|
|ImageDFT.java|114|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'domCol' (lines '114'-'123').|
|ImageDFT.java|119|	AvoidReassigningLoopVariables|	Avoid reassigning the loop control variable 'col'|
|ImageDFT.java|122|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'maxMag' (lines '122'-'128').|
|ImageDFT.java|123|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'domCol' (lines '123'-'123').|
|ImageDFT.java|145|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'fullRow' (lines '145'-'147').|
|ImageDFT.java|147|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'fullRow' (lines '147'-'147').|
|ImageDFT.java|150|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'fftRow' (lines '150'-'150').|
|ImageDFT.java|150|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'fftRow' (lines '150'-'157').|
|ImageDFT.java|165|	UseVarargs|	Consider using varargs for methods or constructors which take an array the last parameter.|
|ImageDFT.java|169|	AvoidLiteralsInIfCondition|	Avoid using Literals in Conditional Statements|
|ImageDFT.java|175|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'even' (lines '175'-'177').|
|ImageDFT.java|177|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'even' (lines '177'-'177').|
|ImageDFT.java|179|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'evenResult' (lines '179'-'195').|
|ImageDFT.java|181|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'odd' (lines '181'-'183').|
|ImageDFT.java|183|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'odd' (lines '183'-'183').|
|ImageDFT.java|185|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'oddResult' (lines '185'-'195').|
|ImageDFT.java|187|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'result' (lines '187'-'191').|
|ImageDFT.java|191|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'result' (lines '191'-'192').|
|ImageDFT.java|192|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'result' (lines '192'-'191').|
|ImageDFT.java|203|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'shifted' (lines '203'-'209').|
|ImageDFT.java|209|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'shifted' (lines '209'-'209').|
|ImageDFT.java|248|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'houghSpace' (lines '248'-'258').|
|ImageDFT.java|252|	AvoidLiteralsInIfCondition|	Avoid using Literals in Conditional Statements|
|ImageDFT.java|258|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'houghSpace' (lines '258'-'258').|
|ImageProcessing.java|13|	UseUtilityClass|	All methods are static.  Consider using a utility class instead. Alternatively, you could add a private constructor or make the class abstract to silence this warning.|
|ImageProcessing.java|13|	ModifiedCyclomaticComplexity|	The class 'ImageProcessing' has a Modified Cyclomatic Complexity of 8 (Highest = 11).|
|ImageProcessing.java|13|	StdCyclomaticComplexity|	The class 'ImageProcessing' has a Standard Cyclomatic Complexity of 8 (Highest = 11).|
|ImageProcessing.java|26|	ModifiedCyclomaticComplexity|	The method 'cosineSimilarity' has a Modified Cyclomatic Complexity of 11.|
|ImageProcessing.java|26|	StdCyclomaticComplexity|	The method 'cosineSimilarity' has a Standard Cyclomatic Complexity of 11.|
|ImageProcessing.java|26|	CognitiveComplexity|	The method 'cosineSimilarity(Image, Image)' has a cognitive complexity of 19, current threshold is 15|
|ImageProcessing.java|26|	CyclomaticComplexity|	The method 'cosineSimilarity(Image, Image)' has a cyclomatic complexity of 14.|
|ImageProcessing.java|26|	NPathComplexity|	The method 'cosineSimilarity(Image, Image)' has an NPath complexity of 320, current threshold is 200|
|ImageProcessing.java|39|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'v1' (lines '39'-'63').|
|ImageProcessing.java|40|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'v2' (lines '40'-'70').|
|ImageProcessing.java|40|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'v2' (lines '40'-'84').|
|ImageProcessing.java|42|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'img1SolidColour' (lines '42'-'64').|
|ImageProcessing.java|43|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'img2SolidColour' (lines '43'-'71').|
|ImageProcessing.java|51|	LawOfDemeter|	Potential violation of Law of Demeter (object not created locally)|
|ImageProcessing.java|52|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'prevGrayVal2' (lines '52'-'84').|
|ImageProcessing.java|52|	LawOfDemeter|	Potential violation of Law of Demeter (object not created locally)|
|ImageProcessing.java|53|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'grayVal1' (lines '53'-'63').|
|ImageProcessing.java|53|	UnusedAssignment|	The initializer for variable 'grayVal1' is never used (overwritten on line 63)|
|ImageProcessing.java|54|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'grayVal2' (lines '54'-'70').|
|ImageProcessing.java|54|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'grayVal2' (lines '54'-'84').|
|ImageProcessing.java|54|	UnusedAssignment|	The initializer for variable 'grayVal2' is never used (overwritten on line 70)|
|ImageProcessing.java|63|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'v1' (lines '63'-'63').|
|ImageProcessing.java|63|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'v1' (lines '63'-'84').|
|ImageProcessing.java|64|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'img1SolidColour' (lines '64'-'64').|
|ImageProcessing.java|67|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'index2' (lines '67'-'84').|
|ImageProcessing.java|70|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'v2' (lines '70'-'70').|
|ImageProcessing.java|70|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'v2' (lines '70'-'84').|
|ImageProcessing.java|71|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'img2SolidColour' (lines '71'-'71').|
|ImageProcessing.java|75|	LawOfDemeter|	Potential violation of Law of Demeter (method chain calls)|
|ImageProcessing.java|76|	LawOfDemeter|	Potential violation of Law of Demeter (method chain calls)|
|ImageProcessing.java|77|	LawOfDemeter|	Potential violation of Law of Demeter (method chain calls)|
|ImageProcessing.java|80|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'cSim' (lines '80'-'84').|
|ImageProcessing.java|80|	LawOfDemeter|	Potential violation of Law of Demeter (object not created locally)|
|ImageProcessing.java|80|	LawOfDemeter|	Potential violation of Law of Demeter (object not created locally)|
|ImageProcessing.java|80|	LawOfDemeter|	Potential violation of Law of Demeter (object not created locally)|
|ImageProcessing.java|82|	LawOfDemeter|	Potential violation of Law of Demeter (object not created locally)|
|ImageProcessing.java|82|	LawOfDemeter|	Potential violation of Law of Demeter (object not created locally)|
|ImageTransformer.java|26|	CyclomaticComplexity|	The class 'ImageTransformer' has a total cyclomatic complexity of 99 (highest 9).|
|ImageTransformer.java|26|	TooManyMethods|	This class has too many methods, consider refactoring it.|
|ImageTransformer.java|27|	BeanMembersShouldSerialize|	Found non-transient, non-static member. Please mark as transient or provide accessors.|
|ImageTransformer.java|27|	ImmutableField|	Private field 'image' could be made final; it is only initialized in the declaration or constructor.|
|ImageTransformer.java|28|	BeanMembersShouldSerialize|	Found non-transient, non-static member. Please mark as transient or provide accessors.|
|ImageTransformer.java|28|	ImmutableField|	Private field 'width' could be made final; it is only initialized in the declaration or constructor.|
|ImageTransformer.java|29|	BeanMembersShouldSerialize|	Found non-transient, non-static member. Please mark as transient or provide accessors.|
|ImageTransformer.java|29|	ImmutableField|	Private field 'height' could be made final; it is only initialized in the declaration or constructor.|
|ImageTransformer.java|30|	BeanMembersShouldSerialize|	Found non-transient, non-static member. Please mark as transient or provide accessors.|
|ImageTransformer.java|137|	CognitiveComplexity|	The method 'posterize()' has a cognitive complexity of 18, current threshold is 15|
|ImageTransformer.java|148|	AvoidLiteralsInIfCondition|	Avoid using Literals in Conditional Statements|
|ImageTransformer.java|150|	AvoidLiteralsInIfCondition|	Avoid using Literals in Conditional Statements|
|ImageTransformer.java|156|	AvoidLiteralsInIfCondition|	Avoid using Literals in Conditional Statements|
|ImageTransformer.java|158|	AvoidLiteralsInIfCondition|	Avoid using Literals in Conditional Statements|
|ImageTransformer.java|164|	AvoidLiteralsInIfCondition|	Avoid using Literals in Conditional Statements|
|ImageTransformer.java|166|	AvoidLiteralsInIfCondition|	Avoid using Literals in Conditional Statements|
|ImageTransformer.java|213|	CognitiveComplexity|	The method 'denoise()' has a cognitive complexity of 16, current threshold is 15|
|ImageTransformer.java|252|	LooseCoupling|	Avoid using implementation types like 'ArrayList'; use the interface instead|
|ImageTransformer.java|257|	AvoidLiteralsInIfCondition|	Avoid using Literals in Conditional Statements|
|ImageTransformer.java|271|	CognitiveComplexity|	The method 'weather()' has a cognitive complexity of 16, current threshold is 15|
|ImageTransformer.java|334|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'pixelCount' (lines '334'-'350').|
|ImageTransformer.java|350|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'pixelCount' (lines '350'-'350').|
|ImageTransformer.java|354|	LawOfDemeter|	Potential violation of Law of Demeter (method chain calls)|
|ImageTransformer.java|354|	LawOfDemeter|	Potential violation of Law of Demeter (object not created locally)|
|ImageTransformer.java|355|	LawOfDemeter|	Potential violation of Law of Demeter (method chain calls)|
|ImageTransformer.java|355|	LawOfDemeter|	Potential violation of Law of Demeter (object not created locally)|
|ImageTransformer.java|356|	LawOfDemeter|	Potential violation of Law of Demeter (method chain calls)|
|ImageTransformer.java|356|	LawOfDemeter|	Potential violation of Law of Demeter (object not created locally)|
|ImageTransformer.java|399|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'bgCol' (lines '399'-'399').|
|ImageTransformer.java|399|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'bgCol' (lines '399'-'417').|
|ImageTransformer.java|399|	ForLoopVariableCount|	Too many control variables in the for statement|
|ImageTransformer.java|400|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'bgRow' (lines '400'-'400').|
|ImageTransformer.java|400|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'bgRow' (lines '400'-'417').|
|ImageTransformer.java|400|	ForLoopVariableCount|	Too many control variables in the for statement|
|ImageTransformer.java|404|	AvoidReassigningLoopVariables|	Avoid reassigning the loop control variable 'bgCol'|
|ImageTransformer.java|404|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'bgCol' (lines '404'-'399').|
|ImageTransformer.java|408|	AvoidReassigningLoopVariables|	Avoid reassigning the loop control variable 'bgRow'|
|ImageTransformer.java|408|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'bgRow' (lines '408'-'400').|
|ImageTransformer.java|428|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'region' (lines '428'-'439').|
|ImageTransformer.java|429|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'visited' (lines '429'-'445').|
|ImageTransformer.java|430|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'biggestPixelCount' (lines '430'-'445').|
|ImageTransformer.java|434|	LawOfDemeter|	Potential violation of Law of Demeter (method chain calls)|
|ImageTransformer.java|438|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'biggestPixelCount' (lines '438'-'445').|
|ImageTransformer.java|439|	DataflowAnomalyAnalysis|	Found 'DD'-anomaly for variable 'region' (lines '439'-'439').|
|ImageTransformer.java|457|	UseVarargs|	Consider using varargs for methods or constructors which take an array the last parameter.|
|ImageTransformer.java|461|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'directions' (lines '461'-'499').|
|ImageTransformer.java|475|	OneDeclarationPerLine|	Use one line for each declaration, it enhances code readability.|
|ImageTransformer.java|490|	LawOfDemeter|	Potential violation of Law of Demeter (method chain calls)|
|ImageTransformer.java|604|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'max' (lines '604'-'616').|
|ImageTransformer.java|607|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'max' (lines '607'-'616').|
|Main.java|5|	UnusedImports|	Unused import 'java.awt.Color'|
|Main.java|6|	UnusedImports|	Unused import 'java.util.ArrayList'|
|Main.java|7|	UnusedImports|	Unused import 'java.util.List'|
|Main.java|9|	UseUtilityClass|	All methods are static.  Consider using a utility class instead. Alternatively, you could add a private constructor or make the class abstract to silence this warning.|
|Main.java|14|	UnusedLocalVariable|	Avoid unused local variables such as 'img2'.|
|Main.java|14|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'img2' (lines '14'-'29').|
|Main.java|15|	UnusedLocalVariable|	Avoid unused local variables such as 'img3'.|
|Main.java|15|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'img3' (lines '15'-'29').|
|Main.java|16|	UnusedLocalVariable|	Avoid unused local variables such as 'img4'.|
|Main.java|16|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'img4' (lines '16'-'29').|
|Main.java|17|	UnusedLocalVariable|	Avoid unused local variables such as 'img5'.|
|Main.java|17|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'img5' (lines '17'-'29').|
|Main.java|18|	UnusedLocalVariable|	Avoid unused local variables such as 'img6'.|
|Main.java|18|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'img6' (lines '18'-'29').|
|Main.java|19|	UnusedLocalVariable|	Avoid unused local variables such as 'img7'.|
|Main.java|19|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'img7' (lines '19'-'29').|
|Main.java|20|	UnusedLocalVariable|	Avoid unused local variables such as 'img8'.|
|Main.java|20|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'img8' (lines '20'-'29').|
|Main.java|21|	UnusedLocalVariable|	Avoid unused local variables such as 'img9'.|
|Main.java|21|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'img9' (lines '21'-'29').|
|Main.java|22|	UnusedLocalVariable|	Avoid unused local variables such as 'img'.|
|Main.java|22|	DataflowAnomalyAnalysis|	Found 'DU'-anomaly for variable 'img' (lines '22'-'29').|
|Main.java|28|	LawOfDemeter|	Potential violation of Law of Demeter (object not created locally)|
## Checkstyle Results
### `DoubleMatrix.java`
| Line | Column | Message |
| ---- | ------ | ------- |
| 13 | 32 | `Constant 'epsilon' must be in ALL_CAPS.` |
| 15 | 22 | `Class member (field) 'columns' may not be public.` |
| 16 | 22 | `Class member (field) 'rows' may not be public.` |
| 35 | 36 | `Parameter '_input' must be in camelCase, or consist of a single upper-case letter.` |
### `Image.java`
| Line | Column | Message |
| ---- | ------ | ------- |
| 172 | 9 | `'}' at column 9 should be on the same line as the next part of a multi-block statement (one that directly contains multiple blocks: if/else-if/else, do/while or try/catch/finally).` |
| 193 | 9 | `'}' at column 9 should be on the same line as the next part of a multi-block statement (one that directly contains multiple blocks: if/else-if/else, do/while or try/catch/finally).` |
| 222 | 16 | `'0.299' is a magic number.` |
| 222 | 28 | `'0.587' is a magic number.` |
| 222 | 40 | `'0.114' is a magic number.` |
| 247 | 57 | `'128.0' is a magic number.` |
| 487 | 57 | `'0xFFFFFF' is a magic number.` |
| 538 | 13 | `'}' at column 13 should be on the same line as the next part of a multi-block statement (one that directly contains multiple blocks: if/else-if/else, do/while or try/catch/finally).` |
### `ImageProcessingException.java`
| Line | Column | Message |
| ---- | ------ | ------- |
### `Rectangle.java`
| Line | Column | Message |
| ---- | ------ | ------- |
| 16 | 22 | `Class member (field) 'xTopLeft' may not be public.` |
| 16 | 32 | `Class member (field) 'yTopLeft' may not be public.` |
| 17 | 22 | `Class member (field) 'xBottomRight' may not be public.` |
| 17 | 36 | `Class member (field) 'yBottomRight' may not be public.` |
| 40 | 26 | `Parameter '_xTopLeft' must be in camelCase, or consist of a single upper-case letter.` |
| 40 | 41 | `Parameter '_yTopLeft' must be in camelCase, or consist of a single upper-case letter.` |
| 40 | 56 | `Parameter '_xBottomRight' must be in camelCase, or consist of a single upper-case letter.` |
| 40 | 75 | `Parameter '_yBottomRight' must be in camelCase, or consist of a single upper-case letter.` |
| 41 | 44 | `'||' should be on a new line.` |
### `ColourRegion.java`
| Line | Column | Message |
| ---- | ------ | ------- |
| 3 | 8 | `Unused import - ca.ubc.ece.cpen221.ip.core.Rectangle.` |
| 12 | 16 | `Class member (field) 'xTopLeft' may not be public.` |
| 12 | 26 | `Class member (field) 'yTopLeft' may not be public.` |
| 13 | 16 | `Class member (field) 'xBottomRight' may not be public.` |
| 13 | 30 | `Class member (field) 'yBottomRight' may not be public.` |
| 14 | 16 | `Class member (field) 'pixelCount' may not be public.` |
| 15 | 24 | `Class member (field) 'pixels' may not be public.` |
| 17 | 22 | `Class member (field) 'ogWidth' may not be public.` |
| 18 | 22 | `Class member (field) 'ogHeight' may not be public.` |
| 47 | 29 | `Parameter '_xTopLeft' must be in camelCase, or consist of a single upper-case letter.` |
| 47 | 44 | `Parameter '_yTopLeft' must be in camelCase, or consist of a single upper-case letter.` |
| 47 | 59 | `Parameter '_xBottomRight' must be in camelCase, or consist of a single upper-case letter.` |
| 47 | 78 | `Parameter '_yBottomRight' must be in camelCase, or consist of a single upper-case letter.` |
| 48 | 44 | `'||' should be on a new line.` |
### `Complex.java`
| Line | Column | Message |
| ---- | ------ | ------- |
| 17 | 20 | `'(' is preceded with whitespace.` |
| 25 | 25 | `'(' is preceded with whitespace.` |
| 25 | 28 | `'{' at column 28 should have line break after.` |
| 30 | 25 | `'(' is preceded with whitespace.` |
| 30 | 28 | `'{' at column 28 should have line break after.` |
| 38 | 24 | `'(' is preceded with whitespace.` |
| 48 | 29 | `'(' is preceded with whitespace.` |
| 58 | 26 | `'(' is preceded with whitespace.` |
| 67 | 29 | `'(' is preceded with whitespace.` |
| 77 | 41 | `'(' is preceded with whitespace.` |
### `ImageDFT.java`
| Line | Column | Message |
| ---- | ------ | ------- |
| 12 | 11 | `'static' modifier out of order with the JLS suggestions.` |
| 13 | 11 | `'static' modifier out of order with the JLS suggestions.` |
| 15 | 11 | `'static' modifier out of order with the JLS suggestions.` |
| 16 | 11 | `'static' modifier out of order with the JLS suggestions.` |
| 17 | 11 | `'static' modifier out of order with the JLS suggestions.` |
| 29 | 21 | `'(' is preceded with whitespace.` |
| 58 | 38 | `'(' is preceded with whitespace.` |
| 58 | 41 | `'{' at column 41 should have line break after.` |
| 63 | 39 | `'(' is preceded with whitespace.` |
| 63 | 42 | `'{' at column 42 should have line break after.` |
| 71 | None | `Line is longer than 120 characters (found 121).` |
| 84 | 50 | `'0xFF' is a magic number.` |
| 88 | 75 | `';' is not followed by whitespace.` |
| 106 | 10 | `'if' has incorrect indentation level 9, expected level should be 8.` |
| 106 | 26 | `'{' at column 26 should have line break after.` |
| 116 | 92 | `')' is preceded with whitespace.` |
| 117 | 51 | `')' is preceded with whitespace.` |
| 119 | 25 | `'if' child has incorrect indentation level 24, expected level should be 20.` |
| 119 | 29 | `Control variable 'col' is modified.` |
| 121 | 46 | `')' is preceded with whitespace.` |
| 169 | 9 | `'if' construct must use '{}'s.` |
| 189 | 31 | `'-2.0' is a magic number.` |
| 256 | None | `Line is longer than 120 characters (found 126).` |
### `ImageProcessing.java`
| Line | Column | Message |
| ---- | ------ | ------- |
| 7 | 1 | `Redundant import from the java.lang package - java.lang.Math.` |
| 7 | 8 | `Unused import - java.lang.Math.` |
| 51 | 49 | `'0xFF' is a magic number.` |
| 52 | 49 | `'0xFF' is a magic number.` |
| 63 | 41 | `Inner assignments should be avoided.` |
| 63 | 68 | `'0xFF' is a magic number.` |
| 64 | 17 | `'if' construct must use '{}'s.` |
| 64 | 17 | `'if' is not followed by whitespace.` |
| 64 | 17 | `'if' is not followed by whitespace.` |
| 70 | 41 | `Inner assignments should be avoided.` |
| 70 | 68 | `'0xFF' is a magic number.` |
| 71 | 17 | `'if' construct must use '{}'s.` |
| 71 | 17 | `'if' is not followed by whitespace.` |
| 71 | 17 | `'if' is not followed by whitespace.` |
| 80 | None | `Line is longer than 120 characters (found 121).` |
| 80 | 46 | `'(' is followed by whitespace.` |
| 80 | 120 | `')' is preceded with whitespace.` |
| 81 | 9 | `'if' construct must use '{}'s.` |
| 82 | 14 | `'if' construct must use '{}'s.` |
| 83 | 9 | `'else' construct must use '{}'s.` |
| 91 | None | `Line is longer than 120 characters (found 129).` |
| 94 | 9 | `'if' is not followed by whitespace.` |
| 94 | 9 | `'if' is not followed by whitespace.` |
| 100 | 9 | `'for' is not followed by whitespace.` |
| 100 | 9 | `'for' is not followed by whitespace.` |
### `ImageSimilarity.java`
| Line | Column | Message |
| ---- | ------ | ------- |
### `ImageTransformer.java`
| Line | Column | Message |
| ---- | ------ | ------- |
| 74 | 47 | `'24' is a magic number.` |
| 74 | 53 | `'0xFF' is a magic number.` |
| 75 | 45 | `'16' is a magic number.` |
| 75 | 51 | `'0xFF' is a magic number.` |
| 76 | 46 | `'24' is a magic number.` |
| 76 | 60 | `'16' is a magic number.` |
| 76 | 72 | `'8' is a magic number.` |
| 93 | 9 | `'for' is not followed by whitespace.` |
| 93 | 9 | `'for' is not followed by whitespace.` |
| 116 | 47 | `'24' is a magic number.` |
| 116 | 53 | `'0xFF' is a magic number.` |
| 117 | 45 | `'16' is a magic number.` |
| 117 | 51 | `'0xFF' is a magic number.` |
| 118 | 47 | `'8' is a magic number.` |
| 118 | 52 | `'0xFF' is a magic number.` |
| 119 | 44 | `'0xFF' is a magic number.` |
| 121 | 47 | `'24' is a magic number.` |
| 121 | 55 | `'255' is a magic number.` |
| 121 | 69 | `'16' is a magic number.` |
| 121 | 77 | `'255' is a magic number.` |
| 121 | 93 | `'8' is a magic number.` |
| 121 | 99 | `'255' is a magic number.` |
| 143 | 47 | `'24' is a magic number.` |
| 143 | 53 | `'0xFF' is a magic number.` |
| 144 | 45 | `'16' is a magic number.` |
| 144 | 51 | `'0xFF' is a magic number.` |
| 145 | 47 | `'8' is a magic number.` |
| 145 | 52 | `'0xFF' is a magic number.` |
| 146 | 44 | `'0xFF' is a magic number.` |
| 148 | 17 | `'if' is not followed by whitespace.` |
| 148 | 17 | `'if' is not followed by whitespace.` |
| 148 | 27 | `'64' is a magic number.` |
| 149 | 27 | `'32' is a magic number.` |
| 150 | 35 | `'128' is a magic number.` |
| 151 | 27 | `'96' is a magic number.` |
| 153 | 27 | `'222' is a magic number.` |
| 156 | 17 | `'if' is not followed by whitespace.` |
| 156 | 17 | `'if' is not followed by whitespace.` |
| 156 | 29 | `'64' is a magic number.` |
| 157 | 29 | `'32' is a magic number.` |
| 158 | 37 | `'128' is a magic number.` |
| 159 | 29 | `'96' is a magic number.` |
| 161 | 29 | `'222' is a magic number.` |
| 164 | 17 | `'if' is not followed by whitespace.` |
| 164 | 17 | `'if' is not followed by whitespace.` |
| 164 | 28 | `'64' is a magic number.` |
| 165 | 28 | `'32' is a magic number.` |
| 166 | 36 | `'128' is a magic number.` |
| 167 | 28 | `'96' is a magic number.` |
| 169 | 28 | `'222' is a magic number.` |
| 172 | 49 | `'24' is a magic number.` |
| 172 | 63 | `'16' is a magic number.` |
| 172 | 79 | `'8' is a magic number.` |
| 209 | None | `Line is longer than 120 characters (found 123).` |
| 223 | 17 | `'for' is not followed by whitespace.` |
| 223 | 17 | `'for' is not followed by whitespace.` |
| 224 | 21 | `'for' is not followed by whitespace.` |
| 224 | 21 | `'for' is not followed by whitespace.` |
| 225 | 25 | `'if' is not followed by whitespace.` |
| 225 | 25 | `'if' is not followed by whitespace.` |
| 226 | 73 | `'16' is a magic number.` |
| 226 | 79 | `'0xFF' is a magic number.` |
| 227 | 75 | `'8' is a magic number.` |
| 227 | 80 | `'0xFF' is a magic number.` |
| 228 | 72 | `'0xFF' is a magic number.` |
| 233 | 47 | `'24' is a magic number.` |
| 233 | 53 | `'0xFF' is a magic number.` |
| 238 | 44 | `'24' is a magic number.` |
| 238 | 58 | `'16' is a magic number.` |
| 238 | 74 | `'8' is a magic number.` |
| 257 | 9 | `'if' is not followed by whitespace.` |
| 257 | 9 | `'if' is not followed by whitespace.` |
| 284 | 73 | `'16' is a magic number.` |
| 284 | 79 | `'0xFF' is a magic number.` |
| 285 | 75 | `'8' is a magic number.` |
| 285 | 80 | `'0xFF' is a magic number.` |
| 286 | 72 | `'0xFF' is a magic number.` |
| 291 | 47 | `'24' is a magic number.` |
| 291 | 53 | `'0xFF' is a magic number.` |
| 296 | 48 | `'24' is a magic number.` |
| 296 | 62 | `'16' is a magic number.` |
| 296 | 78 | `'8' is a magic number.` |
| 340 | 9 | `'for' is not followed by whitespace.` |
| 340 | 9 | `'for' is not followed by whitespace.` |
| 341 | 13 | `'for' is not followed by whitespace.` |
| 341 | 13 | `'for' is not followed by whitespace.` |
| 342 | 54 | `'16' is a magic number.` |
| 342 | 60 | `'0xFF' is a magic number.` |
| 343 | 56 | `'8' is a magic number.` |
| 343 | 61 | `'0xFF' is a magic number.` |
| 344 | 53 | `'0xFF' is a magic number.` |
| 358 | 17 | `'0xFF' is a magic number.` |
| 358 | 37 | `'24' is a magic number.` |
| 358 | 51 | `'16' is a magic number.` |
| 358 | 67 | `'8' is a magic number.` |
| 404 | 27 | `Control variable 'bgCol' is modified.` |
| 408 | 27 | `Control variable 'bgRow' is modified.` |
| 428 | 49 | `',' is not followed by whitespace.` |
| 428 | 51 | `',' is not followed by whitespace.` |
| 428 | 53 | `',' is not followed by whitespace.` |
| 457 | 30 | `'(' is preceded with whitespace.` |
| 463 | 17 | `'{' is followed by whitespace.` |
| 463 | 36 | `'{' is followed by whitespace.` |
| 464 | 17 | `'{' is followed by whitespace.` |
| 464 | 27 | `'{' is followed by whitespace.` |
| 464 | 36 | `'{' is followed by whitespace.` |
| 487 | 20 | `'(' is followed by whitespace.` |
| 524 | 66 | `'+' is not preceded with whitespace.` |
| 525 | 67 | `'+' is not preceded with whitespace.` |
| 528 | 9 | `'for' is not followed by whitespace.` |
| 528 | 9 | `'for' is not followed by whitespace.` |
| 529 | 13 | `'for' is not followed by whitespace.` |
| 529 | 13 | `'for' is not followed by whitespace.` |
| 530 | 21 | `Local variable 'original_x' must be in camelCase, or consist of a single upper-case letter.` |
| 530 | 84 | `'+' should be on a new line.` |
| 532 | 21 | `Local variable 'original_y' must be in camelCase, or consist of a single upper-case letter.` |
| 532 | 85 | `'+' should be on a new line.` |
| 534 | 56 | `'&&' should be on a new line.` |
| 535 | 44 | `'&&' should be on a new line.` |
| 536 | 45 | `')' is preceded with whitespace.` |
| 561 | 9 | `'for' is not followed by whitespace.` |
| 561 | 9 | `'for' is not followed by whitespace.` |
| 562 | 13 | `'for' is not followed by whitespace.` |
| 562 | 13 | `'for' is not followed by whitespace.` |
| 564 | 48 | `'0xFF' is a magic number.` |
| 564 | 57 | `'16' is a magic number.` |
| 564 | 73 | `'0xFF' is a magic number.` |
| 564 | 82 | `'8' is a magic number.` |
| 564 | 95 | `'0xFF' is a magic number.` |
| 585 | 9 | `'for' is not followed by whitespace.` |
| 585 | 9 | `'for' is not followed by whitespace.` |
| 586 | 13 | `'for' is not followed by whitespace.` |
| 586 | 13 | `'for' is not followed by whitespace.` |
| 588 | 48 | `'0xFF' is a magic number.` |
| 588 | 57 | `'16' is a magic number.` |
| 588 | 73 | `'0xFF' is a magic number.` |
| 588 | 82 | `'8' is a magic number.` |
| 588 | 95 | `'0xFF' is a magic number.` |
| 605 | 9 | `'for' is not followed by whitespace.` |
| 605 | 9 | `'for' is not followed by whitespace.` |
| 606 | 13 | `'for' is not followed by whitespace.` |
| 606 | 13 | `'for' is not followed by whitespace.` |
| 607 | 17 | `'if' construct must use '{}'s.` |
| 610 | 9 | `'for' is not followed by whitespace.` |
| 610 | 9 | `'for' is not followed by whitespace.` |
| 611 | 13 | `'for' is not followed by whitespace.` |
| 611 | 13 | `'for' is not followed by whitespace.` |
### `Main.java`
| Line | Column | Message |
| ---- | ------ | ------- |
| 5 | 8 | `Unused import - java.awt.Color.` |
| 6 | 8 | `Unused import - java.util.ArrayList.` |
| 7 | 8 | `Unused import - java.util.List.` |

