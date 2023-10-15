using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Default10_Part2 : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {

    }


    protected void StudentsList_SelectedIndexChanged(object sender, EventArgs e)
    {
        StudentNameLbl.Visible = true;
        StudentNameLbl.Text = "Имя: " + StudentsList.SelectedItem.Value.ToString();
    }
}