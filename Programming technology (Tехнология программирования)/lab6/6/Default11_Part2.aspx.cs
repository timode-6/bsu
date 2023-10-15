using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;

public partial class Default11_Part2 : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        HtmlTable table1 = new HtmlTable();
        table1.Border = 1;
        table1.CellPadding = 20;
        table1.CellSpacing = 3;
        table1.BorderColor = "red";
        HtmlTableRow row;
        HtmlTableCell cell;
        for (int i = 1; i <= 3; i++)
        {
            row = new HtmlTableRow();
            for (int j = 1; j <= 4; j++)
            {
                cell = new HtmlTableCell();
                cell.BgColor = (((i + j) & 1) == 0 ? "lightyellow" : "lightcyan");
                row.Cells.Add(cell);
            }
            table1.Rows.Add(row);
        }
        table1.Rows[0].Cells[0].InnerText = "Группа 1";
        table1.Rows[0].Cells[1].InnerText = "2020";
        this.Controls.Add(table1);
    }
}