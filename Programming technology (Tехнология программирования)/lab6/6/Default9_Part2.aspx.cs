using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Default9_Part2 : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {

    }

    // Makes label for student name visible and sets text, which corresponds the
    // name of the selected student.
    protected void StudentsList_SelectedIndexChanged(object sender, EventArgs e)
    {
        StudentNameLbl.Visible = true;
        StudentNameLbl.Text = "Имя: " + StudentsList.SelectedItem.Value.ToString();
    }

    /* Makes label for country name visible and sets text, which corresponds the
     the selected town.*/
    protected void TownsList_SelectedIndexChanged(object sender, EventArgs e)
    {
        CountryLbl.Visible = true;
        CountryLbl.Text = "Страна: " + TownsList.SelectedItem.Value.ToString();
    }
}