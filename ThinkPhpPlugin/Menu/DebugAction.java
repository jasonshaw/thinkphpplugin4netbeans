/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ThinkPhpPlugin.Menu;

import ThinkPhpPlugin.ThinkphpDebug;
import java.net.URL;
import javax.swing.JEditorPane;
import javax.swing.text.JTextComponent;
import org.netbeans.api.editor.EditorRegistry;
import org.openide.awt.HtmlBrowser.URLDisplayer;
import org.openide.cookies.EditorCookie;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.actions.CookieAction;
import org.openide.windows.TopComponent;

public final class DebugAction extends CookieAction {
    private String selectname;
    protected void performAction(Node[] activatedNodes) {
        if(selectname==null || selectname.trim().length()==0)
        {
            runDebugger(" ");
        }
        //EditorCookie editorCookie = activatedNodes[0].getLookup().lookup(EditorCookie.class);
        String select_text="";
        
        try {
            JTextComponent comp = EditorRegistry.lastFocusedComponent();
            select_text = comp.getSelectedText();
            if(select_text!=null && select_text.trim().length()==0)
            {
                runDebugger(select_text);
            }
        }catch (java.lang.NullPointerException e) {
        }
        catch(java.lang.AssertionError ex)
        {
        }
        catch(Exception ex1)
        {
        }
        finally
        {
            runDebugger(select_text);
        }
    }

    public void runDebugger(String select){
        String current_file_path=TopComponent.getRegistry().getActivated().getToolTipText();
        try {
            ThinkphpDebug debuger=new ThinkphpDebug();
            debuger.GetPath(current_file_path);
            String url=debuger.GetDebugUrl(current_file_path, select.trim());
            if(url.isEmpty())
                return;
            try{
                URLDisplayer.getDefault().showURL(new URL(url));
            } catch (Exception eee)
            {
                return;
            }
        } catch (Exception ex) {
            return;
            //Exceptions.printStackTrace(ex);
        }
    }
    
    protected int mode() {
        return CookieAction.MODE_EXACTLY_ONE;
    }

    private String GetSelectAction()
    {
        selectname="";
        try{
            Node[] n = TopComponent.getRegistry().getActivatedNodes();
            if (n.length == 1) {
                EditorCookie ec = (EditorCookie) n[0].getCookie(EditorCookie.class);
                JEditorPane[] panes = ec.getOpenedPanes();
                
                if (panes.length > 0) {
                    for (JEditorPane pane : panes) {
                        if (pane.getSelectedText() != null || pane.getSelectedText().length() > 0) {
                            selectname=pane.getSelectedText();
                            return pane.getSelectedText();
                        }
                    }
                }
            }
        }catch(NullPointerException ex)
        {
        }
        catch(java.lang.AssertionError ex)
        {
        }
        catch(Exception ex)
        {
        }
        finally{
            return selectname;
        }
    }

    public String getName() {
        String name=GetSelectAction();
        if(name==null | name.trim().length()==0)
        {
            ThinkphpDebug debugger=new ThinkphpDebug();
            String current_file_path=TopComponent.getRegistry().getActivated().getToolTipText();
            String action_name=debugger.GetAction(current_file_path);
            if(action_name==null || action_name.trim().length()==0)
            {
                return "无法调试";
            }
            return "调试 "+action_name;
        }
        else
        {
            return "调试 "+name;
        }
        //return NbBundle.getMessage(DebugAction.class, "CTL_DebugAction");
    }

    protected Class[] cookieClasses() {
        return new Class[]{EditorCookie.class};
    }

    @Override
    protected String iconResource() {
        return "ThinkPhpPlugin/59_avatar_middle.png";
    }

    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }

    @Override
    protected boolean asynchronous() {
        return false;
    }
}

