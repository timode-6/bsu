<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Default9_Part2.aspx.cs" 
    Inherits="Default9_Part2" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <div>
            <asp:Label ID="GroupNameLbl" runat="server" Text="Группа 1" Font-Size="Large"/>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <asp:Label ID="TownsLbl" runat="server" Text="Города" Font-Size="Large"/>
            <br/>
            <%--Some of stufents from the first group. In the ListBox you can see only the
                surname. If you want to know the name, then you should choose the student 
                in the list by clicking on it.--%>
            <asp:ListBox ID="StudentsList" runat="server" 
                OnSelectedIndexChanged="StudentsList_SelectedIndexChanged" AutoPostBack="True">
                <asp:ListItem Value="Тимофей">Алексейчик</asp:ListItem>
                <asp:ListItem Value="Даниил">Басак</asp:ListItem>
                <asp:ListItem Value="Сергей">Гаращенко</asp:ListItem>
                <asp:ListItem Value="Ольга">Гринько</asp:ListItem>
                <asp:ListItem Value="Ян">Дубовский</asp:ListItem>
                <asp:ListItem Value="Александра">Зинькевич</asp:ListItem>
                <asp:ListItem Value="Антон">Кириевич</asp:ListItem>
                <asp:ListItem Value="Николай">Климович</asp:ListItem>
            </asp:ListBox>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <asp:ListBox ID="TownsList" runat="server" 
                OnSelectedIndexChanged="TownsList_SelectedIndexChanged" AutoPostBack="True">
                <asp:ListItem Value="Беларусь">Минск</asp:ListItem>
                <asp:ListItem Value="Россия">Москва</asp:ListItem>
                <asp:ListItem Value="Украина">Киев</asp:ListItem>
                <asp:ListItem Value="Польша">Варшава</asp:ListItem>
                <asp:ListItem Value="США">Вашингтон</asp:ListItem>
                <asp:ListItem Value="Италия">Рим</asp:ListItem>
                <asp:ListItem Value="Чехия">Прага</asp:ListItem>
                <asp:ListItem Value="Франция">Париж</asp:ListItem>
                <asp:ListItem Value="Китай">Пекин</asp:ListItem>
                <asp:ListItem Value="Испания">Мадрид</asp:ListItem>
            </asp:ListBox>
            <br/><br/>
            <asp:Label ID="StudentNameLbl" runat="server" Text="" Font-Size="Large" 
                Visible="false"/>
            &nbsp;&nbsp;&nbsp;
            <asp:Label ID="CountryLbl" runat="server" Text="" Font-Size="Large" 
                Visible="false"/>
        </div>
    </form>
</body>
</html>
