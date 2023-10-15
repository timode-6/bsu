<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Default10_Part2.aspx.cs" 
    Inherits="Default10_Part2" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <div>
            <asp:DropDownList runat="server" ID="StudentsList"
                OnSelectedIndexChanged="StudentsList_SelectedIndexChanged" AutoPostBack="True">
                <asp:ListItem Selected="True" Value="Тимофей">Алексейчик</asp:ListItem>
                <asp:ListItem Value="Даниил">Басак</asp:ListItem>
                <asp:ListItem Value="Сергей">Гаращенко</asp:ListItem>
                <asp:ListItem Value="Ольга">Гринько</asp:ListItem>
                <asp:ListItem Value="Ян">Дубовский</asp:ListItem>
                <asp:ListItem Value="Александра">Зинькевич</asp:ListItem>
                <asp:ListItem Value="Антон">Кириевич</asp:ListItem>
                <asp:ListItem Value="Николай">Климович</asp:ListItem>
            </asp:DropDownList>
            <br/><br/>
            <asp:Label ID="StudentNameLbl" runat="server" Text="" Font-Size="Large" 
                Visible="false"/>
        </div>
    </form>
</body>
</html>
