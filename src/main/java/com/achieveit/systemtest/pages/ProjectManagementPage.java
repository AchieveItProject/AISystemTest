package com.achieveit.systemtest.pages;

import com.achieveit.systemtest.entity.ProjectInfo;
import javafx.util.Pair;
import org.hamcrest.CoreMatchers;
import org.hamcrest.core.AllOf;
import org.hamcrest.core.AnyOf;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.BiFunction;
import java.util.function.Function;

import static com.achieveit.systemtest.drivers.DriverSingleton.pauseOperation;
import static com.achieveit.systemtest.pages.Page.wait;
import static com.achieveit.systemtest.pages.Page.webDriver;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

public class ProjectManagementPage implements Page {

    @FindBy(xpath = "/html/body/div/div/section/section/section/section/main/div/div[1]/div[2]/button")
    @CacheLookup
    private WebElement newProjectButton;

    @FindBy(xpath = "/html/body/div[1]/div/section/section/section/section/main/div/div[1]/div[1]/div/div/div[1]/div/button")
    @CacheLookup
    private WebElement searchButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div/section/section/section/section/main/div/div[2]/div[3]/table/tbody")
    private WebElement projectList;
    // Take care of it, here's complete ver.
    @FindBy(xpath = " /html/body/div[1]/div/section/section/section/section/main/div/div[2]/div[4]/div[2]/table/tbody")
    private WebElement projectListExtension;

    private WebElement selectedElement;
    private WebElement selectedListItem;

    public ProjectManagementPage(Page p) {
        wait.until(wd -> wd.getTitle().equals("项目管理"));
        PageFactory.initElements(webDriver, this);
    }

    // select Element function
    public ProjectManagementPage selectProjectListElement(int row, int column) {
        WebElement currentRow = projectList.findElements(By.tagName("tr")).get(row);
        WebElement currentElement = currentRow.findElements(By.tagName("td")).get(column);
        this.selectedElement = currentElement.findElement(By.tagName("div"));
        return this;
    }



    public int selectListItemPosition(BiFunction<WebElement, WebElement, Boolean> testMethod) {
        WebElement pageNumbers = webDriver.findElement(
                By.xpath("//*[@id=\"app\"]/div/section/section/section/section/main/div/div[3]/ul"));
        List<WebElement> numbers = pageNumbers.findElements(By.className("number"));
        WebElement curPageNumber = pageNumbers.findElement(By.className("active"));
        System.out.println(numbers.size());
        // Set to the first page
        if (!numbers.get(0).equals(curPageNumber)) {
            numbers.get(0).click();
            pauseOperation(500);
//            while (!curPageNumber.getAttribute("class").contains("active")) {

//                    numbers.get(0).click();
//                    pauseOperation(500);

//            }
            curPageNumber = pageNumbers.findElement(By.className("active"));
        }
        // Start enumeration
        int allPage = Integer.parseInt(numbers.get(numbers.size() - 1).getText());
        int matchLineNumber = -1;
        for (int j = 0; j < allPage; j++) {

            List<WebElement> lines = projectList.findElements(By.tagName("tr"));
            List<WebElement> linesExt = projectListExtension.findElements(By.tagName("tr"));
            matchLineNumber = -1;
            for (int i = 0; i < lines.size(); i++) {

                WebElement line = lines.get(i);
                WebElement lineExt = linesExt.get(i);
                // Condition!
                if (testMethod.apply(line, lineExt)) {
                    matchLineNumber = i;
                    break;
                }

            }
            // No match statement,next page
            // Find the first id to check if it's jumped to next page
            String oldPageFirstId = lines.get(0).findElements(By.tagName("td")).get(2)
                    .findElement(By.tagName("div")).getText();
            if (matchLineNumber == -1) {
                String newPageFirstId = oldPageFirstId;
//                while(newPageFirstId.equals(oldPageFirstId)) {
                WebElement next = webDriver.findElement(
                        By.xpath("/html/body/div/div/section/section/section/section/main/div/div[3]/button[2]/i"));
                Actions actions = new Actions(webDriver);
                actions.moveToElement(next).click().perform();
                pauseOperation(500);
                newPageFirstId = lines.get(0).findElements(By.tagName("td")).get(2)
                        .findElement(By.tagName("div")).getText();
//                }
                if (newPageFirstId.equals(oldPageFirstId)) break;

            } else {
                break;
            }
        }
        return matchLineNumber;

    }

    public List<WebElement> selectListItem(BiFunction<WebElement, WebElement, Boolean> testMethod) {
        int matchLineNumber = selectListItemPosition(testMethod);
        // Here, matchLineNumber is the result of target Element!
        if (matchLineNumber == -1) return null;
        WebElement matchLine = projectList.findElements(By.tagName("tr")).get(matchLineNumber);
        WebElement matchLineExt = projectList.findElements(By.tagName("tr")).get(matchLineNumber);

        List<WebElement> l = new ArrayList<WebElement>();
        l.add(matchLine);
        l.add(matchLineExt);
        return l;
    }
    public  WebElement acquireStatusButton(WebElement lineExt){
       return lineExt.findElements(By.tagName("td")).get(8).findElement(By.tagName("div")).findElement(By.tagName("button"));
    }
    public List< WebElement> acquireOperationButton(WebElement line){
        List<WebElement> e = line.findElements(By.tagName("td")).get(9).findElement(By.tagName("div")).findElements(By.tagName("i"));
        return e;
    }
    // Check function
    public void checkProjectInfoExists(ProjectInfo projectInfo, boolean expectedResult) {
        int res = selectListItemPosition((line, lineExt) -> translateFromLineWebElement(line, lineExt).equals(projectInfo));
        if (expectedResult)
            assertThat("There's no project in list which is equal to this projectInfo.", res, IsNot.not(-1));
        else {
            assertThat("There's a project in list which is equal to this projectInfo.", res, Is.is(-1));
        }

    }


    public void checkProjectNameContains(String name, boolean expectedResult) {
        List<String> checkedId;
        checkedId = new ArrayList<>();

        List<WebElement> e = selectListItem((line, lineExt) -> checkedId.contains(translateFromLineWebElement(line, lineExt).getId()));
        while (e != null) {
            ProjectInfo p = translateFromLineWebElement(e.get(0), e.get(1));
            checkedId.add(p.getId());
            assertThat(p.getProjectName(), containsString(name));
            e = selectListItem((line, lineExt) -> checkedId.contains(translateFromLineWebElement(line, lineExt).getId()));
        }


    }

    public void checkProjectLeader(String name, boolean expectedResult) {
        List<String> checkedId;
        checkedId = new ArrayList<>();

        List<WebElement> e = selectListItem((line, lineExt) -> checkedId.contains(translateFromLineWebElement(line, lineExt).getId()));
        while (e != null) {
            ProjectInfo p = translateFromLineWebElement(e.get(0), e.get(1));
            checkedId.add(p.getId());
            assertThat(p.getLeader(), CoreMatchers.is(name));
            e = selectListItem((line, lineExt) -> checkedId.contains(translateFromLineWebElement(line, lineExt).getId()));
        }


    }
    // Revise!
    public void checkProjectInfo(ProjectInfo projectInfo, boolean expectedResult) {
        if (expectedResult) {
            selectProjectListElement(0, 3);
            assertThat(selectedElement.getText(), Is.is(projectInfo.getProjectName()));
            selectProjectListElement(0, 4);
            assertThat(selectedElement.getText(), Is.is(projectInfo.getLeader()));
        } else {
            selectProjectListElement(0, 3);
            assertThat(selectedElement.getText(), IsNot.not(projectInfo.getProjectName()));
//            selectProjectListElement(0, 4);
//            assertThat(selectedElement.getText(), IsNot.not(projectInfo.getProjectName()));
        }
    }

    public ProjectManagementPage checkListAmount(int expected) {
        WebElement total = webDriver.findElement(By.xpath("/html/body/div[1]/div/section/section/section/section/main/div/div[3]/span[1]"));
        assertThat(Integer.parseInt(total.getText().substring(2, 3)), Is.is(new Integer(expected)));
        return this;
    }
   public ProjectManagementPage checkStatusButtonClickable(WebElement lineExt,boolean expectedResult){
        acquireStatusButton(lineExt);
        clickStatusButton(lineExt);
        pauseOperation(500);
       Boolean hasPopUp= webDriver.findElement(By.xpath("/html/body")).getAttribute("class").equals("el-popup-parent--hidden");
            assertThat(hasPopUp,Is.is(expectedResult));
            // Remove dialog
            Actions a=new Actions(webDriver);
            a.moveByOffset(10,10).click().perform();
        return this;
    }

    // Function call for test
    public ProjectInfo translateFromLineWebElement(WebElement line, WebElement lineExt) {
        ProjectInfo r = new ProjectInfo();
        List<WebElement> columns = line.findElements(By.tagName("td"));
        r.setId(columns.get(2).findElement(By.tagName("div")).getText());
        r.setProjectName(columns.get(3).findElement(By.tagName("div")).getText());
        r.setLeader(columns.get(4).findElement(By.tagName("div")).getText());
        r.setCreatorId(columns.get(5).findElement(By.tagName("div")).getText());
        r.setScheduleDate(columns.get(6).findElement(By.tagName("div")).getText());
        r.setDeliveryDate(columns.get(7).findElement(By.tagName("div")).getText());
        List<WebElement> columns2 = lineExt.findElements(By.tagName("td"));
        r.setStatus(columns2.get(8).findElement(By.tagName("div")).findElement(By.tagName("button")).getText());
        System.out.println(columns2.get(8).findElement(By.tagName("div")).findElement(By.tagName("button")).getText());

        return r;
    }
    public int catchProjectId(ProjectInfo p) {
        List<WebElement> i = selectListItem((line, lineExt) -> translateFromLineWebElement(line, lineExt).equals(p));
        ProjectInfo pComplete = translateFromLineWebElement(i.get(0), i.get(1));
        return Integer.parseInt(pComplete.getId());
    }

    // New and search project operations
    public ProjectCreateDialogPage clickNewProjectButton() {
           newProjectButton.click();
            pauseOperation(500);

        return new ProjectCreateDialogPage((Page) this);
    }
    public ProjectSearchSubPage clickSearchButtonToOpenSearchDialog() {
        while (webDriver.findElement(By.xpath("/html/body/div[1]/div/section/section/section/section/main/div/div[1]/div[1]/div/div/div[2]")).getCssValue("display").equals("none")) {
            searchButton.click();
        }
        return new ProjectSearchSubPage(this);
    }

    // Button at each line operations
    public ProjectEditDialogPage clickModifyButton(int row) {
        selectProjectListElement(row, 9);
        // Find Edit Dialog. If is none, then repeat to do it.
        while (webDriver.findElement(By.xpath("/html/body/div/div/section/section/section/section/main/div/div[4]")).getCssValue("display").equals("none")) {
            Actions chain = new Actions(webDriver);
            List<WebElement> e = selectedElement.findElements(By.tagName("i"));
            chain.moveToElement(e.get(e.size() - 2)).click().perform();
            pauseOperation(500);
        }
        return new ProjectEditDialogPage((Page) this);
    }

    public ProjectDeleteDialogPage clickDeleteButton(WebElement line) {
        Actions chain = new Actions(webDriver);
        List<WebElement> e = line.findElements(By.tagName("td")).get(9).findElement(By.tagName("div")).findElements(By.tagName("i"));
        chain.moveToElement(e.get(e.size() - 1)).click().perform();
        pauseOperation(500);
        return new ProjectDeleteDialogPage((Page) this);
    }

    private void clickDetailButtonOperation(WebElement line){
        Actions chain = new Actions(webDriver);
        List<WebElement> e = line.findElements(By.tagName("td")).get(9).findElement(By.tagName("div")).findElements(By.tagName("i"));
        chain.moveToElement(e.get(0)).click().perform();
        pauseOperation(500);
    }
    public ProjectDetailPage clickDetailButton(WebElement line) {
        clickDetailButtonOperation(line);
        return new ProjectDetailPage((Page) this);
    }

    public AchievePage clickDetailButtonByCM(WebElement line) {
        clickDetailButtonOperation(line);
        return new AchievePage((Page) this);
    }

    private void clickStatusButton(WebElement lineExt) {
        WebElement statusButton = lineExt.findElements(By.tagName("td")).get(8).findElement(By.tagName("div")).findElement(By.tagName("button"));
        Actions action = new Actions(webDriver);
        action.moveToElement(statusButton).click().perform();
        pauseOperation(500);
    }

    public ProjectApprovalPage clickApplyStatusButtonByLeader(WebElement lineExt) {
        clickStatusButton(lineExt);
        return new ProjectApprovalPage(this);
    }

    public ChangeToInProgressPage clickApprovalStatusButtonByPM(WebElement lineExt) {
        clickStatusButton(lineExt);
        return new ChangeToInProgressPage(this);
    }

    public AssignEPGPage clickApprovalStatusButtonByEPG(WebElement lineExt) {
        clickStatusButton(lineExt);
        return new AssignEPGPage(this);
    }

    public AssignQAPage clickApprovalStatusButtonByQA(WebElement lineExt) {
        clickStatusButton(lineExt);
        return new AssignQAPage(this);
    }

    public AssignCMPage clickApprovalStatusButtonByCM(WebElement lineExt) {
        clickStatusButton(lineExt);
        return new AssignCMPage(this);
    }
  public ChangeToSubmitPage  clickProgressStatusButtonByPM(WebElement lineExt){
        clickStatusButton(lineExt);
        return new ChangeToSubmitPage(this);
    }
    public ChangeToEndPage  clickSubmitStatusButtonByPM(WebElement lineExt){
        clickStatusButton(lineExt);
        return new ChangeToEndPage(this);
    }
    public ChangeToAchievePage   clickEndButtonByCM(WebElement lineExt){
        clickStatusButton(lineExt);
        return new ChangeToAchievePage(this);
    }
}
